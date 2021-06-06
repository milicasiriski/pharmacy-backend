package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.PharmacistServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PharmacistServiceImplTest {

    private PharmacistService subject;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Mock
    private AuthenticationService authenticationService;
/*
    @BeforeEach
    public void setup() {
        subject = new PharmacistServiceImpl(pharmacistRepository, authenticationService);
    }

    @Test
    public void testGetAllPharmacistsInPharmacy() {
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PHARMACY_ADMIN);

        // WHEN
        Iterable<Pharmacist> pharmacists = subject.getAllPharmacists();

        // THEN
        assertTrue(true);
    }

    @Test
    public void testGetAllPharmacistsInSystem() {
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PATIENT);

        // WHEN
        Iterable<Pharmacist> pharmacists = subject.getAllPharmacists();

        // THEN
        assertTrue(true);
    }*/

}
