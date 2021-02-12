package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ComplaintAnswerDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.ComplaintServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComplaintServiceTest {

    @Autowired
    PharmacistRepository pharmacistRepository;

    @Autowired
    DermatologistRepository dermatologistRepository;

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    UserRepository userRepository;

    @Mock
    MailService<String> mailService;

    @Autowired
    PharmacyRepository pharmacyRepository;

    @Mock
    AuthenticationService authenticationService;

    private ComplaintServiceImpl subject;

    @BeforeEach
    public void setup() {
        subject = new ComplaintServiceImpl(pharmacistRepository, dermatologistRepository, complaintRepository,
                userRepository, mailService, pharmacyRepository, authenticationService);
    }

    @Test
    public void testGetUnresolvedComplaints() {
        List<ComplaintDto> result = subject.getUnresolvedComplains();
        assertTrue(result.size() >= 1);
    }

    @Test
    public void testResolveComplaint() {
        ComplaintAnswerDto dto = new ComplaintAnswerDto(1L, "foo");
        try {
            subject.resolveComplaint(dto);
        } catch (Exception e) {
            fail();
        }
    }

}
