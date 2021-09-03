package rs.ac.uns.ftn.isa.pharmacy.demo.service;


import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Prescription;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDto;

import java.util.List;

public interface PatientService {
    Iterable<MedicinesBasicInfoDto> getAllAllergies();

    void updateAllergies(List<MedicinesBasicInfoDto> medicine);

    Iterable<MedicinesBasicInfoDto> getAllMedicine();

    boolean hasCurrentUserExceededPenaltyPoints();

    boolean hasPatientExceededPenaltyPoints(String patientID);

    void resetPenaltyPoints();

    Iterable<Prescription> getAllPrescriptions();

    List<PatientDto> findAll();

    Patient getPatientByEmail(String email);
}
