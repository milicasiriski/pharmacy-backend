package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PatientService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, MedicineRepository medicineRepository) {
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Iterable<MedicinesBasicInfoDto> getAllAllergies() {
        List<MedicinesBasicInfoDto> result = new ArrayList<>();
        Patient patient = getSignedInUser();
        medicineRepository.findAllergiesForPatient(patient.getId()).forEach(medicine ->
                result.add(new MedicinesBasicInfoDto(medicine)));
        return result;
    }

    @Override
    public void updateAllergies(List<MedicinesBasicInfoDto> medicine) throws EntityNotFoundException {
        List<Medicine> allergies = new ArrayList<>();
        medicine.forEach(m -> {
            Medicine allergy = getMedicineById(m.getId());
            if (!allergies.contains(allergy)) {
                allergies.add(allergy);
            }
        });
        // TODO: check if cascade type is ok
        Patient patient = getSignedInUser();
        patient.setAllergies(allergies);
        patientRepository.save(patient);
    }

    @Override
    public Iterable<MedicinesBasicInfoDto> getAllMedicine() {
        List<MedicinesBasicInfoDto> result = new ArrayList<>();
        medicineRepository.findAll().forEach(medicine -> result.add(new MedicinesBasicInfoDto(medicine)));
        return result;
    }

    private Medicine getMedicineById(Long id) throws EntityNotFoundException {
        Optional<Medicine> medicineOptional = medicineRepository.findById(id);
        if (medicineOptional.isPresent()) {
            return medicineOptional.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
