package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.TimeInterval;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistExamSchedulingService;

import java.util.*;

@Service
public class PharmacistExamSchedulingServiceImpl implements PharmacistExamSchedulingService {
    private final PharmacyRepository pharmacyRepository;
    private final PharmacistRepository pharmacistRepository;

    public PharmacistExamSchedulingServiceImpl(PharmacyRepository pharmacyRepository, PharmacistRepository pharmacistRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacistRepository = pharmacistRepository;
    }

    @Override
    public Iterable<Pharmacy> getPharmaciesWithAvailableAppointments(Date dateTime) {
        Iterable<Pharmacist> pharmacists = pharmacistRepository.getAll();
        List<Pharmacy> pharmacies = new ArrayList<>();

        pharmacists.forEach(pharmacist -> {
            Pharmacy pharmacy = pharmacist.getPharmacy();
            int duration = pharmacy.getPharmacistExamDuration();
            TimeInterval appointment = new TimeInterval(getCalendarFromDate(dateTime), duration);

            if (isAppointmentAvailable(appointment, pharmacist) && !pharmacies.contains(pharmacy)) {
                pharmacies.add(pharmacy);
            }
        });

        return pharmacies;
    }

    private boolean isAppointmentAvailable(TimeInterval appointment, Pharmacist pharmacist) {
        return isAppointmentInPharmacistsShift(appointment, pharmacist) &&
                !isAppointmentOnPharmacistsVacation(appointment, pharmacist) &&
                !isAppointmentOverlappingWithScheduled(appointment, pharmacist);
    }

    private boolean isAppointmentInPharmacistsShift(TimeInterval appointment, Pharmacist pharmacist) {
        // TODO: test
        DaysOfWeek dayOfWeek = DaysOfWeek.fromCalendarDayOfWeek(appointment.getDayOfWeek());
        Map<DaysOfWeek, TimeInterval> shifts = pharmacist.getShifts();
        if (!shifts.containsKey(dayOfWeek)) {
            return false;
        }
        TimeInterval shift = shifts.get(dayOfWeek);
        return appointment.isTimeInside(shift);
    }

    private boolean isAppointmentOnPharmacistsVacation(TimeInterval appointment, Pharmacist pharmacist) {
        // TODO:
        return false;
    }

    private boolean isAppointmentOverlappingWithScheduled(TimeInterval appointment, Pharmacist pharmacist) {
        // TODO:
        return false;
    }

    private Calendar getCalendarFromDate(Date date) {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        return result;
    }
}
