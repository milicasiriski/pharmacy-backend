package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.DermatologistHasShiftInAnotherPharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.PharmacyServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PharmacyServiceImplTest {

    @Mock
    private PharmacyRepository pharmacyRepository;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private MedicineRepository medicineRepository;

    @Mock
    private PharmacistRepository pharmacistRepository;

    @Mock
    private DermatologistRepository dermatologistRepository;

    @Mock
    private MedicineReservationRepository medicineReservationRepository;

    @Mock
    private AuthenticationService authenticationService;

    private PharmacyServiceImpl subject;
/*
    @BeforeEach
    void setUp() {
        subject = new PharmacyServiceImpl(pharmacyRepository, medicineRepository, pharmacistRepository, dermatologistRepository,
                medicineReservationRepository, examRepository, authenticationService);
    }

    @Test
    void testAddingDermatologistToPharmacy_ShiftsAreNotOverlapping_SavesPharmacy() {
        // GIVEN
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PHARMACY_ADMIN);
        when(dermatologistRepository.findById(any())).thenReturn(Optional.of(TestConstants.DERMATOLOGIST));
        when(pharmacyRepository.findPharmacyByPharmacyAdmin(any())).thenReturn(TestConstants.PHARMACY_TEST_OBJECT_2);

        // WHEN
        subject.addDermatologist(TestConstants.ADD_DERMATOLOGIST_DTO);

        // THEN
        verify(pharmacyRepository, times(1)).save(any());
    }

    @Test
    void testAddingDermatologistToPharmacy_ShiftsAreOverlapping_ThrowsException() {
        // GIVEN
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PHARMACY_ADMIN);
        when(dermatologistRepository.findById(any())).thenReturn(Optional.of(TestConstants.DERMATOLOGIST));
        when(pharmacyRepository.findPharmacyByPharmacyAdmin(any())).thenReturn(TestConstants.PHARMACY_TEST_OBJECT_2);

        // THEN
        assertThrows(DermatologistHasShiftInAnotherPharmacy.class, () -> subject.addDermatologist(TestConstants.ADD_DERMATOLOGIST_DTO_2));
    }
*/
}
