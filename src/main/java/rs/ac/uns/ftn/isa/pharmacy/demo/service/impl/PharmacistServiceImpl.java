package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.PharmacyAdmin;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthenticationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistService;

import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public PharmacistServiceImpl(PharmacistRepository pharmacistRepository, AuthenticationService authenticationService) {
        this.pharmacistRepository = pharmacistRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public List<Pharmacist> getPharmacistsByPharmacy(Long pharmacyId) {
        return pharmacistRepository.getByPharmacy(pharmacyId);
    }

    @Override
    public Iterable<Pharmacist> getAllPharmacists() {
        if (((authenticationService.getLoggedUser().getAdministrationRole()).equals("ROLE_PHARMACY_ADMINISTRATOR"))) {
            PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) authenticationService.getLoggedUser();
            return getPharmacistsByPharmacy(pharmacyAdmin.getPharmacy().getId());
        } else {
            return pharmacistRepository.findAll();
        }
    }
}
