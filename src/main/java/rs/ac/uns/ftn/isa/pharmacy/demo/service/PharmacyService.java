package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

import java.util.List;

public interface PharmacyService {
    List<Pharmacy> findAll();
    Iterable<Pharmacy> findAllPharmaciesByPharmacyAdmin(Long pharmacyAdminId);
}
