package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SchedulePharmacistExamParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacistSortType;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacySortType;

import java.util.Date;

public interface PharmacistExamSchedulingService {
    Iterable<Pharmacy> getPharmaciesWithAvailableAppointments(Date dateTime, PharmacySortType sortType);

    Iterable<Pharmacist> getPharmacistsWithAvailableAppointments(Date dateTime, long pharmacyId, PharmacistSortType sortType);

    void scheduleAppointment(SchedulePharmacistExamParams params, Patient patient);
}
