package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;

import java.util.List;

public interface PharmacistService {

    List<Pharmacist> getAllPharmacists();
    List<Pharmacist> getPharmacistsByPharmacy(String pharmacyName);
}
