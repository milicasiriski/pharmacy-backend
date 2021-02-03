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
        Calendar start = getCalendarFromDate(dateTime);
        List<Pharmacy> pharmacies = new ArrayList<>();

        pharmacists.forEach(pharmacist -> {
            // TODO: get exam duration and check if the end is in time interval
            DaysOfWeek dayOfWeek = DaysOfWeek.fromCalendarDayOfWeek(start.get(Calendar.DAY_OF_WEEK));
            Map<DaysOfWeek, TimeInterval> shifts = pharmacist.getShifts();

            if (shifts.containsKey(dayOfWeek)) {
                TimeInterval shift = shifts.get(dayOfWeek);
                Pharmacy pharmacy = pharmacist.getPharmacy();
                if (shift.containsTime(start) && !pharmacies.contains(pharmacy)) {
                    pharmacies.add(pharmacy);
                }
            }
        });

        return pharmacies;
    }

    private Calendar getCalendarFromDate(Date date) {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        return result;
    }

    private TimeInterval createTimeInterval(Date dateTime, int duration) {
        Calendar start = getCalendarFromDate(dateTime);
        Calendar end = getCalendarFromDate(dateTime);
        end.add(Calendar.MINUTE, duration);
        return new TimeInterval(start, end);
    }
}
