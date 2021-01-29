package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.MedicineReservationServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicineReservationServiceImplTest {
    @Mock
    private MedicineRepository medicineRepository;
    @Mock
    private MedicineReservationRepository medicineReservationRepository;
    @Mock
    private PharmacyRepository pharmacyRepository;
    @Mock
    private MailService<MedicineReservationEmailParams> mailService;

    private MedicineReservationServiceImpl subject;

    @BeforeEach
    public void setUp() {
        subject = new MedicineReservationServiceImpl(medicineRepository, medicineReservationRepository, pharmacyRepository, mailService);
    }

    @Test
    public void testIsReservationValid_PharmacyHasMedicineOnStock_ReturnsTrue() {
        // GIVEN
        when(medicineRepository.findById(any())).thenReturn(Optional.of(TestConstants.MEDICINE_TEST_OBJECT));
        when(pharmacyRepository.findById(any())).thenReturn(Optional.of(TestConstants.PHARMACY_TEST_OBJECT));

        // WHEN
        boolean result = subject.isReservationValid(TestConstants.MEDICINE_RESERVATION_DTO_TEST_OBJECT);

        // THEN
        assertTrue(result);
    }

    @Test
    public void testIsReservationValid_MedicineDoesNotExist_ReturnsFalse() {
        // WHEN
        boolean result = subject.isReservationValid(TestConstants.MEDICINE_RESERVATION_DTO_TEST_OBJECT);

        // THEN
        assertFalse(result);
    }

    @Test
    public void testIsReservationValid_PharmacyDoesNotExist_ReturnsFalse() {
        // GIVEN
        when(medicineRepository.findById(any())).thenReturn(Optional.of(TestConstants.MEDICINE_TEST_OBJECT));

        // WHEN
        boolean result = subject.isReservationValid(TestConstants.MEDICINE_RESERVATION_DTO_TEST_OBJECT);

        // THEN
        assertFalse(result);
    }

    @Test
    public void testIsReservationValid_PharmacyDoesNotHaveMedicineOnStock_ReturnsFalse() {
        // GIVEN
        when(medicineRepository.findById(any())).thenReturn(Optional.of(TestConstants.MEDICINE_TEST_OBJECT));
        when(pharmacyRepository.findById(any())).thenReturn(Optional.of(TestConstants.PHARMACY_STOCK_EMPTY_TEST_OBJECT));

        // WHEN
        boolean result = subject.isReservationValid(TestConstants.MEDICINE_RESERVATION_DTO_TEST_OBJECT);

        // THEN
        assertFalse(result);
    }

    @Test
    public void testIsReservationValid_PharmacyDoesNotHaveMedicine_ReturnsFalse() {
        // GIVEN
        when(medicineRepository.findById(any())).thenReturn(Optional.of(TestConstants.MEDICINE_TEST_OBJECT));
        when(pharmacyRepository.findById(any())).thenReturn(Optional.of(TestConstants.PHARMACY_MEDICINE_EMPTY_TEST_OBJECT));

        // WHEN
        boolean result = subject.isReservationValid(TestConstants.MEDICINE_RESERVATION_DTO_TEST_OBJECT);

        // THEN
        assertFalse(result);
    }
}
