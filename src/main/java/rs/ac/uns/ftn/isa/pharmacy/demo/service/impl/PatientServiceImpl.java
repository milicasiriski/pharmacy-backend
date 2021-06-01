package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Prescription;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PrescriptionRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthenticationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PenaltyPointsConstants;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, MedicineRepository medicineRepository, PrescriptionRepository prescriptionRepository, AuthenticationService authenticationService) {
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public List<PatientDto> findAll() {
        List<PatientDto> dtoPatients = new ArrayList<>();

        patientRepository.findAll().forEach(patient -> dtoPatients.add(new PatientDto(patient)));

        return dtoPatients;
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

    @Override
    public boolean hasCurrentUserExceededPenaltyPoints() {
        Patient patient = getSignedInUser();
        return patient.getPenaltyPoints() >= PenaltyPointsConstants.PENALTY_POINT_LIMIT;
    }

    @Override
    public void resetPenaltyPoints() {
        patientRepository.findAll().forEach(patient -> {
            patient.resetPenaltyPoints();
            patientRepository.save(patient);
        });
    }

    @Override
    public Iterable<Prescription> getAllPrescriptions() {
        Patient patient = getSignedInUser();
        return prescriptionRepository.findByPatient(patient);
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
        return (Patient) authenticationService.getLoggedUser();
    }
}
