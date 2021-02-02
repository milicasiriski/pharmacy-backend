package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamAlreadyScheduledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamCannotBeCancelledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.ExamConfirmationMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExamAndDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.ExamRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistEmploymentService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ExamService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.ExamSortType;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    private final DermatologistEmploymentService dermatologistEmploymentService;
    private final PharmacyRepository pharmacyRepository;
    private final ExamRepository examRepository;
    private final MailService<TimeInterval> mailService;

    @Autowired
    public ExamServiceImpl(DermatologistEmploymentService dermatologistEmploymentService, PharmacyRepository pharmacyRepository, ExamRepository examRepository, MailService<TimeInterval> mailService) {
        this.dermatologistEmploymentService = dermatologistEmploymentService;
        this.pharmacyRepository = pharmacyRepository;
        this.examRepository = examRepository;
        this.mailService = mailService;
    }

    @Override
    public void createExam(PharmacyAdminExamDto pharmacyAdminExamDto) {
        Exam exam = new Exam(pharmacyAdminExamDto.getPrice(), generateExamTimeInterval(pharmacyAdminExamDto));
        Dermatologist dermatologist = dermatologistEmploymentService.getDermatologistById(Long.parseLong(pharmacyAdminExamDto.getDermatologistId()));

        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getId());
        Map<Dermatologist, Employment> dermatologists = pharmacy.getDermatologists();
        Employment employment = dermatologists.get(dermatologist);

        employment.getExams().add(exam);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void scheduleDermatologistExam(long examId, Patient patient) throws ExamAlreadyScheduledException, EntityNotFoundException, MessagingException {
        if (isExamAvailable(examId)) {
            Exam exam = getExamById(examId);
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
        List<ExamAndDermatologistDto> result = new ArrayList<>();

        Map<Dermatologist, Employment> map = pharmacy.getDermatologists();
        Set<Dermatologist> dermatologists = map.keySet();

        dermatologists.forEach(key -> map.get(key).getExams().stream()
                .filter(it -> !it.isScheduled()).forEach(exam -> result.add(new ExamAndDermatologistDto(exam, key))));
        sortExams(result, sortType);
        return result;
    }

    @Override
    public Iterable<ExamDetails> getDermatologistExamsForPatient(Patient patient) {
        return examRepository.getExamDetails(patient.getId());
    }

    @Override
    public void cancelDermatologistExam(long examId) throws EntityNotFoundException, ExamCannotBeCancelledException {
        Exam exam = getExamById(examId);
        if (!exam.isCancellable()) {
            throw new ExamCannotBeCancelledException();
        }
        exam.cancel();
        examRepository.save(exam);
    }

    @Override
    public void deleteExam(long examId) throws EntityNotFoundException {

        Exam exam = examRepository.findById(examId).orElse(null);

        if (exam == null) {
            throw new EntityNotFoundException();
        } else {
            examRepository.delete(exam);
        }
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
        examEnd.set(Calendar.HOUR, examStart.get(Calendar.HOUR));
        examEnd.set(Calendar.MINUTE, examStart.get(Calendar.MINUTE));
        examEnd.set(Calendar.SECOND, examStart.get(Calendar.SECOND));
        examEnd.add(Calendar.MINUTE, pharmacyAdminExamDto.getDuration());

        return new TimeInterval(examStart, examEnd);
    }
}