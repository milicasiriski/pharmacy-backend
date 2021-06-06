package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PrescriptionRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.PatientServiceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientServiceImplTest {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Mock
    private AuthenticationService authenticationService;
    private PatientService subject;
/*
    @BeforeEach
    public void setup() {
        subject = new PatientServiceImpl(patientRepository, medicineRepository, prescriptionRepository, authenticationService);
    }

    @Test
    public void testGetPharmacists() {
        // GIVEN
        Patient patient = new Patient();
        patient.setId(3L);
        when(authenticationService.getLoggedUser()).thenReturn(patient);

        // WHEN
        Iterable<MedicinesBasicInfoDto> result = subject.getAllAllergies();

        // THEN
        assertTrue(true);
    }*/
}
