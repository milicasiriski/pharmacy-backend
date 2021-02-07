package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.PharmacyAdmin;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DermatologistServiceImpl implements DermatologistService {

    private final DermatologistRepository dermatologistRepository;
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, PharmacyRepository pharmacyRepository) {
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Iterable<Dermatologist> getAllDermatologists() {
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdministrationRole().equals("ROLE_PHARMACY_ADMINISTRATOR")) {
            PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return getDermatologistsByPharmacy(pharmacyAdmin.getPharmacy().getId());
        } else {
            return dermatologistRepository.findAll();
        }
    }

    @Override
    public List<Dermatologist> getDermatologistsByPharmacy(Long pharmacyId) {
        return dermatologistRepository.getDermatologistsByPharmacy(pharmacyId);
    }

    @Override
    public List<DermatologistDto> getOtherDermatologists() throws EntityNotFoundException {

        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyAdmin.getPharmacy().getId()).orElse(null);

        if(pharmacy == null) {
            throw new EntityNotFoundException();
        }

        Iterable<Dermatologist> allDermatologists = dermatologistRepository.findAll();
        Set<Dermatologist> dermatologistInPharmacy = pharmacy.getDermatologists().keySet();
        List<DermatologistDto> otherDermatologist = new ArrayList<>();

        allDermatologists.forEach(dermatologist -> {
            if (!dermatologistInPharmacy.contains(dermatologist)) {
                DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), dermatologist.getEmail(), dermatologist.getId());
                otherDermatologist.add(dermatologistDto);
            }
        });

        return otherDermatologist;
    }
}
