package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthenticationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DermatologistServiceImpl implements DermatologistService {

    private final DermatologistRepository dermatologistRepository;
    private final PharmacyRepository pharmacyRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, PharmacyRepository pharmacyRepository, AuthenticationService authenticationService) {
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public Iterable<Dermatologist> getAllDermatologists() {
        if (authenticationService.getLoggedUser().getAdministrationRole().equals("ROLE_PHARMACY_ADMINISTRATOR")) {
            PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) authenticationService.getLoggedUser();
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

    @Override
    public List<PharmacyDto> getDermatologistsPharmacies(long dermatologistID) {
        Dermatologist dermatologist = dermatologistRepository.getDermatologistById(dermatologistID);
        Map<Pharmacy, Employment> pharmacies = dermatologist.getPharmacies();
        List<PharmacyDto> pharmacyDtos = new ArrayList<>();
        for (Pharmacy p: pharmacies.keySet()) {
            pharmacyDtos.add(new PharmacyDto(p));
        }
        return pharmacyDtos;
    }
}
