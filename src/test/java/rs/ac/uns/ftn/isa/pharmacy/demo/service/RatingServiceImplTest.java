package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExaminerRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.RatingServiceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RatingServiceImplTest {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private DermatologistRepository dermatologistRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private RatingMedicineRepository ratingMedicineRepository;
    @Autowired
    private RatingDermatologistRepository ratingDermatologistRepository;
    @Autowired
    private RatingPharmacistRepository ratingPharmacistRepository;
    @Autowired
    private RatingPharmacyRepository ratingPharmacyRepository;
    @Mock
    private AuthenticationService authenticationService;
    private RatingService subject;
/*
    @BeforeEach
    public void setup() {
        subject = new RatingServiceImpl(pharmacyRepository,
                dermatologistRepository,
                pharmacistRepository,
                medicineRepository,
                ratingMedicineRepository,
                ratingDermatologistRepository,
                ratingPharmacistRepository,
                ratingPharmacyRepository,
                authenticationService);
    }

    @Test
    public void testGetPharmacists() {
        // GIVEN
        Patient patient = new Patient();
        patient.setId(3L);
        when(authenticationService.getLoggedUser()).thenReturn(patient);

        // WHEN
        Iterable<ExaminerRatingDto> result = subject.getDermatologists();

        // THEN
        assertTrue(true);
    }*/
}
