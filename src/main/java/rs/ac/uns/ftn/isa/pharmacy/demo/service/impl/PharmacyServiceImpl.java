package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Address;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<PharmacyDto> findAll() {
        List<PharmacyDto> dtoPharmacies = new ArrayList<>();

        pharmacyRepository.findAll().forEach(pharmacy -> dtoPharmacies.add(new PharmacyDto(pharmacy)));

        return dtoPharmacies;
    }

    @Override
    public Pharmacy findPharmacyByPharmacyAdmin(Long pharmacyAdminId) {
        return pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdminId);
    }

    @Override
    public List<PharmacyNameAndAddressDto> findPharmaciesBasicInfo() {
        List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

        pharmacyRepository.findAll().forEach(pharmacy -> {
            dtoPharmacies.add(new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress().getStreet()));
        });

        return dtoPharmacies;
    }

    @Override
    public PharmacyProfileDto findPharmacyById(Long pharmacyId) throws EntityNotFoundException {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElse(null);

        if (pharmacy == null) {
            throw new EntityNotFoundException();
        }

        PharmacyDto pharmacyDto = new PharmacyDto(pharmacy);

        List<DermatologistDto> dermatologists = findDermatologists(pharmacy);
        List<PharmacistDto> pharmacists = findPharmacists(pharmacy);
        List<MedicinesBasicInfoDto> medicines = findMedicines(pharmacy);

        return new PharmacyProfileDto(pharmacyDto, dermatologists, pharmacists, medicines);
    }

    private List<MedicinesBasicInfoDto> findMedicines(Pharmacy pharmacy) {
        List<MedicinesBasicInfoDto> medicines = new ArrayList<>();

        pharmacy.getMedicine().keySet().forEach(medicine -> {
            MedicinesBasicInfoDto medicinesBasicInfoDto = new MedicinesBasicInfoDto(medicine.getName(), medicine.getForm().label, medicine.getId(), medicine.getRatings());
            medicines.add(medicinesBasicInfoDto);
        });
        return medicines;
    }

    private List<PharmacistDto> findPharmacists(Pharmacy pharmacy) {
        List<PharmacistDto> pharmacists = new ArrayList<>();

        pharmacy.getPharmacists().forEach(pharmacist -> {
            PharmacistDto pharmacistDto = new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(), 4.5, pharmacist.getId());
            pharmacists.add(pharmacistDto);
        });
        return pharmacists;
    }

    private List<DermatologistDto> findDermatologists(Pharmacy pharmacy) {
        List<DermatologistDto> dermatologists = new ArrayList<>();

        pharmacy.getDermatologists().keySet().forEach(dermatologist -> {
            DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.5, dermatologist.getId());
            dermatologists.add(dermatologistDto);
        });
        return dermatologists;
    }

    @Override
    public Pharmacy save(PharmacyDto dto) {
        // TODO: Give real address to constructor
        Pharmacy pharmacy = new Pharmacy(dto.getName(), new Address(), dto.getAbout());
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacy;
    }
}