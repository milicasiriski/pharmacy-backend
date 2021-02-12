package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PrescriptionUsedException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientShoppingEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.QRResultDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.QRServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class QRServiceImplTest {
    @Mock
    private PharmacyRepository pharmacyRepository;

    @Mock
    private MedicineRepository medicineRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @Mock
    private MailService<PatientShoppingEmailParams> mailService;

    @Mock
    private MedicinePurchaseRepository medicinePurchaseRepository;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private LoyaltyService loyaltyService;

    private QRServiceImpl subject;

    @BeforeEach
    void setUp() {
        subject = new QRServiceImpl(pharmacyRepository, medicineRepository, userRepository, prescriptionRepository, mailService, medicinePurchaseRepository, loyaltyService, authenticationService);
    }

    @Test
    void testFindPharmacies_twoPharmaciesHaveMedicineInStock_ReturnsTwoResults() {
        // GIVEN
        when(pharmacyRepository.findAll()).thenReturn(TestConstants.PHARMACY_TEST_LIST);
        when(loyaltyService.getDiscount()).thenReturn(0.8);
        when(medicineRepository.findByUuid(any())).thenReturn(TestConstants.MEDICINE_TEST_OBJECT);
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PATIENT_TEST_OBJECT);

        // WHEN
        List<QRResultDto> result = subject.findPharmacies(TestConstants.PRESCRIPTION_DTO);

        // THEN
        assertEquals(2, result.size());
    }

    @Test
    void testFindPharmacies_NotFound_ThrowsNotFoundException() {
        // GIVEN
        when(pharmacyRepository.findAll()).thenReturn(TestConstants.PHARMACY_TEST_LIST);
        when(medicineRepository.findByUuid(any())).thenReturn(TestConstants.MEDICINE_TEST_OBJECT_2);
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PATIENT_TEST_OBJECT);

        assertThrows(NoMedicineFoundException.class, () -> subject.findPharmacies(TestConstants.PRESCRIPTION_DTO));
    }

    @Test
    void testBuy_successful() throws Exception {
        // GIVEN
        when(prescriptionRepository.findById(any())).thenReturn(Optional.empty());
        when(pharmacyRepository.findById(any())).thenReturn(Optional.of(TestConstants.PHARMACY_TEST_OBJECT));
        when(loyaltyService.getDiscount()).thenReturn(0.8);
        when(medicineRepository.findByUuid(any())).thenReturn(TestConstants.MEDICINE_TEST_OBJECT);
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PATIENT_TEST_OBJECT);

        // WHEN
        subject.buy(new QRResultDto() {{
            setPrescription(TestConstants.PRESCRIPTION_DTO);
        }});

        //THEN
        verify(pharmacyRepository, times(1)).save(any());
        verify(medicinePurchaseRepository, times(1)).save(any());
        verify(prescriptionRepository, times(1)).save(any());

    }

    @Test
    void testBuy_PrescriptionUsedException_thrown() {
        // GIVEN
        when(prescriptionRepository.findById(any())).thenReturn(Optional.ofNullable(TestConstants.PRESCRIPTION_TEST_OBJECT));

        // THEN
        assertThrows(PrescriptionUsedException.class, () -> subject.buy(new QRResultDto() {{
            setPrescription(TestConstants.PRESCRIPTION_DTO);
        }}));
    }

    @Test
    void testBuy_NoMedicineFound_thrown() {
        // GIVEN
        when(prescriptionRepository.findById(any())).thenReturn(Optional.empty());
        when(medicineRepository.findByUuid(any())).thenReturn(TestConstants.MEDICINE_TEST_OBJECT);
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PATIENT_TEST_OBJECT);
        when(pharmacyRepository.findById(any())).thenReturn(Optional.of(TestConstants.PHARMACY_TEST_OBJECT));

        // THEN
        assertThrows(NoMedicineFoundException.class, () -> subject.buy(new QRResultDto() {{
            setPrescription(TestConstants.PRESCRIPTION_DTO_2);
        }}));
    }


}
