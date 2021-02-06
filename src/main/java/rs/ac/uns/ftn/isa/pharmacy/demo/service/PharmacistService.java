package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;

import java.util.List;

public interface PharmacistService {

    Iterable<Pharmacist> getAllPharmacists();
    List<Pharmacist> getPharmacistsByPharmacy(Long pharmacyId);
}
