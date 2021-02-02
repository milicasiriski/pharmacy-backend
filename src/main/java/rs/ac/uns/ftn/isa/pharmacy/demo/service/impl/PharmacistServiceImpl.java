package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.PharmacyAdmin;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistService;

import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    private final PharmacistRepository pharmacistRepository;

    @Autowired
    public PharmacistServiceImpl(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    @Override
    public List<Pharmacist> getPharmacistsByPharmacy(String pharmacyName) {
        return pharmacistRepository.getPharmacistByPharmacy(pharmacyName);
    }

    @Override
    public List<Pharmacist> getAllPharmacists() {
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdministrationRole().equals("ROLE_PHARMACY_ADMINISTRATOR")) {
            PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return getPharmacistsByPharmacy(pharmacyAdmin.getPharmacy().getName());
        } else {
            return pharmacistRepository.getAllPharmacists();
        }
    }
}
