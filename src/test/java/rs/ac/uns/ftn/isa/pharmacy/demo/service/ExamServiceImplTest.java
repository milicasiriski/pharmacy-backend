package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.ExamConfirmationMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.TimeInterval;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.ExamRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.ExamServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.PatientServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExamServiceImplTest {
    @Autowired
    private DermatologistEmploymentService dermatologistEmploymentService;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private ExamRepository examRepository;
    @Mock
    private MailService<TimeInterval> mailService;
    @Autowired
    private DermatologistVacationRepository dermatologistVacationRepository;
    @Autowired
    private LoyaltyService loyaltyService;
    private ExamService subject;
    @Mock
    private PatientRepository patientRepository;
/*
    @BeforeEach
    public void setup() {
        subject = new ExamServiceImpl(dermatologistEmploymentService,
                pharmacyRepository,
                examRepository,
                mailService,
                dermatologistVacationRepository,
                loyaltyService,
                patientRepository
                );
    }

    @Test
    public void testIsExamAvailable() {
        // WHEN
        boolean result = subject.isExamAvailable(1L);

        // THEN
        assertTrue(true);
    }

    @Test
    public void testGetDermatologistExamHistoryForPatient() {
        // WHEN
        Patient patient = new Patient();
        patient.setId(3L);
        Iterable<ExamDetails> result = subject.getDermatologistExamHistoryForPatient(patient);

        // THEN
        assertTrue(true);
    }*/
}
