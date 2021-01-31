package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Address;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;

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
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy findPharmacyByPharmacyAdmin(Long pharmacyAdminId) {
        return pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdminId);
    }

    public PharmacyProfileDto findPharmacyById(Long pharmacyId) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElse(null);

        if (pharmacy == null) {
            return null;
        }

        Address address = pharmacy.getAddress();
        AddressDto addressDto = new AddressDto(address.getCity(), address.getCountry(), address.getLatitude(), address.getLongitude(), address.getStreet());
        PharmacyDto pharmacyDto = new PharmacyDto(pharmacy.getName(), addressDto, pharmacy.getAbout(), pharmacy.getId(), pharmacy.getRating());

        List<DermatologistDto> dermatologists = new ArrayList<>();
        List<PharmacistDto> pharamcists = new ArrayList<>();
        List<MedicinesBasicInfoDto> medicines = new ArrayList<>();

        pharmacy.getDermatologists().keySet().forEach(dermatologist -> {
            DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.5, dermatologist.getId());
            dermatologists.add(dermatologistDto);
        });

        pharmacy.getPharmacists().forEach(pharmacist -> {
            PharmacistDto pharmacistDto = new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(), 4.5, pharmacist.getId());
            pharamcists.add(pharmacistDto);
        });

        pharmacy.getMedicine().keySet().forEach(medicine -> {
            MedicinesBasicInfoDto medicinesBasicInfoDto = new MedicinesBasicInfoDto(medicine.getName(), medicine.getForm().label, medicine.getId(), medicine.getRatings());
            medicines.add(medicinesBasicInfoDto);
        });

        return new PharmacyProfileDto(pharmacyDto, dermatologists, pharamcists, medicines);
    }

    @Override
    public Pharmacy save(PharmacyDto dto) {
        // TODO: Give real address to constructor
        Pharmacy pharmacy = new Pharmacy(dto.getName(), new Address(), dto.getAbout());
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacy;
    }
}