package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

import java.util.Date;

public interface PharmacistExamSchedulingService {
    Iterable<Pharmacy> getPharmaciesWithAvailableAppointments(Date dateTime);
}
