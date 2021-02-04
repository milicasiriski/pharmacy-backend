package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistExamSchedulingService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacySortType;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class PharmacistExamSchedulingServiceImpl implements PharmacistExamSchedulingService {
    private final PharmacistRepository pharmacistRepository;
    private final PharmacistVacationRepository pharmacistVacationRepository;
    private final PharmacyRepository pharmacyRepository;

    public PharmacistExamSchedulingServiceImpl(PharmacistRepository pharmacistRepository, PharmacistVacationRepository pharmacistVacationRepository, PharmacyRepository pharmacyRepository) {
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacistVacationRepository = pharmacistVacationRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Iterable<Pharmacy> getPharmaciesWithAvailableAppointments(Date dateTime, PharmacySortType sortType) {
        Iterable<Pharmacist> pharmacists = pharmacistRepository.findAll();
        List<Pharmacy> pharmacies = new ArrayList<>();

        pharmacists.forEach(pharmacist -> {
            Pharmacy pharmacy = pharmacist.getPharmacy();
            int duration = pharmacy.getPharmacistExamDuration();
            TimeInterval appointment = new TimeInterval(getCalendarFromDate(dateTime), duration);

            if (isAppointmentAvailable(appointment, pharmacist) && !pharmacies.contains(pharmacy)) {
                pharmacies.add(pharmacy);
            }
        });
        sortPharmacies(pharmacies, sortType);

        return pharmacies;
    }

    @Override
    public Iterable<Pharmacist> getPharmacistsWithAvailableAppointments(Date dateTime, long pharmacyId) {
        Pharmacy pharmacy = getPharmacyById(pharmacyId);
        TimeInterval appointment = new TimeInterval(getCalendarFromDate(dateTime), pharmacy.getPharmacistExamDuration());

        List<Pharmacist> result = new ArrayList<>();
        Iterable<Pharmacist> pharmacists = pharmacistRepository.findByPharmacyId(pharmacyId);
        pharmacists.forEach(pharmacist -> {
            if (isAppointmentAvailable(appointment, pharmacist)) {
                result.add(pharmacist);
            }
        });

        return result;
    }

    private void sortPharmacies(List<Pharmacy> pharmacies, PharmacySortType sortType) {
        switch (sortType) {
            case PRICE_ASC:
                pharmacies.sort(Comparator.comparingDouble(Pharmacy::getPharmacistExamPrice));
                break;
            case PRICE_DESC:
                Comparator<Pharmacy> comparatorPrice = Comparator.comparingDouble(Pharmacy::getPharmacistExamPrice);
                pharmacies.sort(comparatorPrice.reversed());
                break;
            case RATING_ASC:
                pharmacies.sort(Comparator.comparingDouble(Pharmacy::getRating));
                break;
            case RATING_DESC:
                Comparator<Pharmacy> comparatorRating = Comparator.comparingDouble(Pharmacy::getRating);
                pharmacies.sort(comparatorRating.reversed());
                break;
            default:
        }
    }

    private boolean isAppointmentAvailable(TimeInterval appointment, Pharmacist pharmacist) {
        return isAppointmentInPharmacistsShift(appointment, pharmacist) &&
                !isAppointmentOnPharmacistsVacation(appointment, pharmacist) &&
                !isAppointmentOverlappingWithScheduled(appointment, pharmacist);
    }

    private boolean isAppointmentInPharmacistsShift(TimeInterval appointment, Pharmacist pharmacist) {
        DaysOfWeek dayOfWeek = DaysOfWeek.fromCalendarDayOfWeek(appointment.getDayOfWeek());
        Map<DaysOfWeek, TimeInterval> shifts = pharmacist.getShifts();

        if (!shifts.containsKey(dayOfWeek)) {
            return false;
        }
        TimeInterval shift = shifts.get(dayOfWeek);
        return appointment.isTimeInside(shift);
    }

    private boolean isAppointmentOnPharmacistsVacation(TimeInterval appointment, Pharmacist pharmacist) {
        Iterable<VacationTimeRequestPharmacist> vacationRequests =
                pharmacistVacationRepository.findApprovedVacationRequestsForPharmacistOnDay(pharmacist.getId(), appointment.getStart());
        return vacationRequests.iterator().hasNext();
    }

    private boolean isAppointmentOverlappingWithScheduled(TimeInterval appointment, Pharmacist pharmacist) {
        List<Exam> exams = pharmacist.getExams();
        return exams.stream().anyMatch(exam -> appointment.isOverlapping(exam.getTimeInterval()));
    }

    private Calendar getCalendarFromDate(Date date) {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        return result;
    }

    private Pharmacy getPharmacyById(Long id) throws EntityNotFoundException {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(id);
        if (optionalPharmacy.isPresent()) {
            return optionalPharmacy.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
