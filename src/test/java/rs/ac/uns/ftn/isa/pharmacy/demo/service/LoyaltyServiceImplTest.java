package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LoyaltyProgramDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.ExamRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.LoyaltyProgramRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.LoyaltyServiceImpl;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoyaltyServiceImplTest {

    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExamRepository examRepository;

    private LoyaltyService subject;

    @BeforeEach
    public void setup() {
        subject = new LoyaltyServiceImpl(loyaltyProgramRepository, examRepository, userRepository);
    }

    @Test
    public void testUpdate() {
        // WHEN
        try {
            subject.update(new LoyaltyProgramDto(1, 0.9, 2, 0.8, 3));
        } catch (Exception e) {
            fail();
        }
    }

}
