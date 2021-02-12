package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.ExamConfirmationMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExamAndDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.ExamStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.ExamRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistEmploymentService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ExamService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LoyaltyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.ExamSortType;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class ExamServiceImpl implements ExamService {

    private final DermatologistEmploymentService dermatologistEmploymentService;
    private final PharmacyRepository pharmacyRepository;
    private final ExamRepository examRepository;
    private final MailService<TimeInterval> mailService;
    private final DermatologistVacationRepository dermatologistVacationRepository;
    private final LoyaltyService loyaltyService;

    @Autowired
    public ExamServiceImpl(DermatologistEmploymentService dermatologistEmploymentService, PharmacyRepository pharmacyRepository,
                           ExamRepository examRepository, MailService<TimeInterval> mailService, DermatologistVacationRepository dermatologistVacationRepository, LoyaltyService loyaltyService) {
        this.dermatologistVacationRepository = dermatologistVacationRepository;
        this.dermatologistEmploymentService = dermatologistEmploymentService;
        this.pharmacyRepository = pharmacyRepository;
        this.examRepository = examRepository;
        this.mailService = mailService;
        this.loyaltyService = loyaltyService;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void createExam(PharmacyAdminExamDto pharmacyAdminExamDto) throws OptimisticLockException, ShiftIsNotDefinedException, ExamIntervalIsOverlapping, ExamIntervalIsNotInShiftIntervalException, NullPointerException {
        Dermatologist dermatologist = dermatologistEmploymentService.getDermatologistById(pharmacyAdminExamDto.getDermatologistId());

        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getId());

        Map<Dermatologist, Employment> dermatologists = pharmacy.getDermatologists();
        Employment employment = dermatologists.get(dermatologist);

        Map<DaysOfWeek, TimeInterval> shifts = employment.getShifts();
        TimeInterval examTimeInterval = generateExamTimeInterval(pharmacyAdminExamDto);

        String dayOfWeek = DaysOfWeek.fromCalendarDayOfWeek(examTimeInterval.getStart().get(Calendar.DAY_OF_WEEK)).label;

        if (!shifts.containsKey(DaysOfWeek.fromCalendarDayOfWeek(examTimeInterval.getStart().get(Calendar.DAY_OF_WEEK)))) {
            throw new ShiftIsNotDefinedException();
        }

        TimeInterval shiftForDayOfWeek = shifts.get(DaysOfWeek.valueOf(dayOfWeek.toUpperCase()));
        TimeInterval examShiftTimeInterval = getIntervalForShiftCompare(examTimeInterval, shiftForDayOfWeek);

        if (!examShiftTimeInterval.isInside(shiftForDayOfWeek)) {
            throw new ExamIntervalIsNotInShiftIntervalException();
        }

        employment.getExams().forEach(exam -> {
            if (examTimeInterval.isOverlapping(exam.getTimeInterval())) {
                throw new ExamIntervalIsOverlapping();
            }
        });

        Iterable<VacationTimeRequestDermatologist> vacationTimes =
                dermatologistVacationRepository.findApprovedVacationsByDermatologistId(pharmacyAdminExamDto.getDermatologistId());

        vacationTimes.forEach(vacationTime -> {
            if (examTimeInterval.isOverlapping(vacationTime.getRequestedTimeForVacation())) {
                throw new ExamIntervalIsOverlapping();
            }
        });

        Exam exam = new Exam(pharmacyAdminExamDto.getPrice(), examTimeInterval, ExamStatus.WAITING);
        employment.getExams().add(exam);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void scheduleDermatologistExam(long examId, Patient patient) throws ExamAlreadyScheduledException, EntityNotFoundException, MessagingException {
        if (isExamAvailable(examId)) {
            Exam exam = getExamById(examId);
            exam.setPrice(exam.getPrice() * loyaltyService.getDiscount());
            exam.setPatient(patient);
            examRepository.save(exam);

            mailService.sendMail(patient.getEmail(), exam.getTimeInterval(), new ExamConfirmationMailFormatter());
        } else {
            throw new ExamAlreadyScheduledException();
        }
    }

    @Override
    public boolean isExamAvailable(long examId) {
        Exam exam = getExamById(examId);
        return exam.getPatient() == null;
    }

    @Override
    public Iterable<ExamAndDermatologistDto> getAvailableDermatologistExamsForPharmacy(long pharmacyId, ExamSortType sortType) {
        Pharmacy pharmacy = getPharmacyById(pharmacyId);
        List<ExamAndDermatologistDto> result = getAvailableDermatologistAppointmentsForPharmacy(pharmacy);
        sortExams(result, sortType);
        return result;
    }

    @Override
    public Iterable<ExamAndDermatologistDto> getAvailableDermatologistExams(ExamSortType sortType) {
        List<ExamAndDermatologistDto> result = new ArrayList<>();
        pharmacyRepository.findAll().forEach(pharmacy -> {
            List<ExamAndDermatologistDto> exams = getAvailableDermatologistAppointmentsForPharmacy(pharmacy);
            result.addAll(exams);
        });
        sortExams(result, sortType);
        return result;
    }

    @Override
    public Iterable<ExamDetails> getScheduledDermatologistExamsForPatient(Patient patient) {
        return examRepository.getDermatologistScheduledExamDetails(patient.getId());
    }

    @Override
    public Iterable<ExamDetails> getDermatologistExamHistoryForPatient(Patient patient) {
        return examRepository.getDermatologistHistoryExamDetails(patient.getId());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void cancelDermatologistExam(long examId, Patient signedInUser) throws EntityNotFoundException, WrongPatientException, ExamCanNoLongerBeCancelledException {
        Exam exam = getExamById(examId);

        if (!signedInUser.getId().equals(exam.getPatient().getId())) {
            throw new WrongPatientException();
        }
        if (!exam.isCancellable()) {
            throw new ExamCanNoLongerBeCancelledException();
        }
        exam.setPrice(exam.getPrice() / loyaltyService.getDiscount());
        exam.cancel();
        examRepository.save(exam);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void deleteExam(long examId) throws EntityNotFoundException {

        Exam exam = examRepository.findById(examId).orElse(null);

        if (exam == null) {
            throw new EntityNotFoundException();
        } else {
            examRepository.delete(exam);
        }
    }

    private List<ExamAndDermatologistDto> getAvailableDermatologistAppointmentsForPharmacy(Pharmacy pharmacy) {
        List<ExamAndDermatologistDto> result = new ArrayList<>();
        Map<Dermatologist, Employment> map = pharmacy.getDermatologists();
        Set<Dermatologist> dermatologists = map.keySet();

        dermatologists.forEach(key -> map.get(key).getExams().stream()
                .filter(it -> !it.isScheduled()).forEach(exam -> {
                    // TODO: include promotions
                    exam.setPrice(exam.getPrice() * loyaltyService.getDiscount());
                    result.add(new ExamAndDermatologistDto(exam, key));
                }));
        return result;
    }

    private TimeInterval getIntervalForShiftCompare(TimeInterval timeInterval, TimeInterval shiftForDayOfWeek) {
        Calendar examStart = Calendar.getInstance();
        examStart.set(Calendar.YEAR, shiftForDayOfWeek.getStart().get(Calendar.YEAR));
        examStart.set(Calendar.MONTH, shiftForDayOfWeek.getStart().get(Calendar.MONTH));
        examStart.set(Calendar.DAY_OF_MONTH, shiftForDayOfWeek.getStart().get(Calendar.DAY_OF_MONTH));
        examStart.set(Calendar.HOUR_OF_DAY, timeInterval.getStart().get(Calendar.HOUR_OF_DAY));
        examStart.set(Calendar.MINUTE, timeInterval.getStart().get(Calendar.MINUTE));
        examStart.set(Calendar.SECOND, timeInterval.getStart().get(Calendar.SECOND));
        examStart.set(Calendar.MILLISECOND, 0);

        Calendar examEnd = Calendar.getInstance();
        examEnd.set(Calendar.YEAR, shiftForDayOfWeek.getStart().get(Calendar.YEAR));
        examEnd.set(Calendar.MONTH, shiftForDayOfWeek.getStart().get(Calendar.MONTH));
        examEnd.set(Calendar.DAY_OF_MONTH, shiftForDayOfWeek.getStart().get(Calendar.DAY_OF_MONTH));
        examEnd.set(Calendar.HOUR_OF_DAY, timeInterval.getEnd().get(Calendar.HOUR_OF_DAY));
        examEnd.set(Calendar.MINUTE, timeInterval.getEnd().get(Calendar.MINUTE));
        examEnd.set(Calendar.SECOND, timeInterval.getEnd().get(Calendar.SECOND));
        examEnd.set(Calendar.MILLISECOND, 0);

        return new TimeInterval(examStart, examEnd);
    }

    private void sortExams(List<ExamAndDermatologistDto> exams, ExamSortType sortType) {
        switch (sortType) {
            case PRICE_ASC:
                exams.sort(Comparator.comparingDouble(exam -> exam.getExam().getPrice()));
                break;
            case PRICE_DESC:
                Comparator<ExamAndDermatologistDto> comparatorPrice = Comparator.comparingDouble(e -> e.getExam().getPrice());
                exams.sort(comparatorPrice.reversed());
                break;
            case RATING_ASC:
                exams.sort(Comparator.comparingDouble(exam -> exam.getDermatologist().getRating()));
                break;
            case RATING_DESC:
                Comparator<ExamAndDermatologistDto> comparatorRating = Comparator.comparingDouble(e -> e.getDermatologist().getRating());
                exams.sort(comparatorRating.reversed());
                break;
            default:
        }
    }

    private Pharmacy getPharmacyById(Long id) throws EntityNotFoundException {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(id);
        if (optionalPharmacy.isPresent()) {
            return optionalPharmacy.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Exam getExamById(long id) throws EntityNotFoundException {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            return optionalExam.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private TimeInterval generateExamTimeInterval(PharmacyAdminExamDto pharmacyAdminExamDto) {
        Calendar examStart = Calendar.getInstance();
        examStart.setTime(pharmacyAdminExamDto.getExamStart());
        Calendar examEnd = Calendar.getInstance();

        examEnd.set(Calendar.YEAR, examStart.get(Calendar.YEAR));
        examEnd.set(Calendar.MONTH, examStart.get(Calendar.MONTH));
        examEnd.set(Calendar.DAY_OF_MONTH, examStart.get(Calendar.DAY_OF_MONTH));
        examEnd.set(Calendar.HOUR_OF_DAY, examStart.get(Calendar.HOUR_OF_DAY));
        examEnd.set(Calendar.MINUTE, examStart.get(Calendar.MINUTE));
        examEnd.set(Calendar.SECOND, examStart.get(Calendar.SECOND));
        examEnd.add(Calendar.MINUTE, pharmacyAdminExamDto.getDuration());
        examEnd.set(Calendar.MILLISECOND, 0);

        return new TimeInterval(examStart, examEnd);
    }
}