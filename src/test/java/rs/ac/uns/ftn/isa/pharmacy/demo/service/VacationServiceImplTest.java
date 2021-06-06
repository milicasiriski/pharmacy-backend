package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationTimeResponseEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.VacationServiceImpl;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VacationServiceImplTest {

    private VacationService subject;

    @Autowired
    private PharmacistVacationRepository pharmacistVacationRepository;

    @Autowired
    private DermatologistVacationRepository dermatologistVacationRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Mock
    private MailService<VacationTimeResponseEmailParams> mailService;

    @Mock
    private AuthenticationService authenticationService;
/*
    @BeforeEach
    public void setup() {
        subject = new VacationServiceImpl(pharmacistVacationRepository, dermatologistVacationRepository, vacationRepository, mailService, authenticationService);
    }

    @Test
    public void testGetAllDermatologistVacationInPharmacy() {
        // GIVEN
        when(authenticationService.getLoggedUser()).thenReturn(TestConstants.PHARMACY_ADMIN);

        // WHEN
        Iterable<VacationTimeRequestDermatologist> dermatologistVacations = subject.getAllDermatologistsVacation();

        // THEN
        assertTrue(true);
    }
*/
}
