package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;

@Service
public class PharmacyService {

    private PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Iterable<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }
}
