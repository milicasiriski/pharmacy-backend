package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.DermatologistServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DermatologistServiceImplTest {

    private DermatologistService subject;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        subject = new DermatologistServiceImpl(dermatologistRepository, pharmacyRepository, authenticationService);
    }

    @Test
    public void testGetAllDermatologistsInPharmacy() {
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PHARMACY_ADMIN);

        // WHEN
        Iterable<Dermatologist> dermatologists = subject.getAllDermatologists();

        // THEN
        assertTrue(dermatologists.iterator().hasNext());
    }

    @Test
    public void testGetAllDermatologistsInSystem() {
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PATIENT);

        // WHEN
        Iterable<Dermatologist> dermatologists = subject.getAllDermatologists();

        // THEN
        assertTrue(dermatologists.iterator().hasNext());
    }

}
