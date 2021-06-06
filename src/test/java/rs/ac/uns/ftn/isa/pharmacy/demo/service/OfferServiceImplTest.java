package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.OfferServiceImpl;


import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfferServiceImplTest {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    PharmacyRepository pharmacyRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Mock
    MailService<Boolean> mailService;

    @Mock
    AuthenticationService authenticationService;

    private OfferServiceImpl subject;
/*
    @BeforeEach
    public void setup() {
        subject = new OfferServiceImpl(offerRepository, orderRepository, userRepository,
                medicineRepository, pharmacyRepository, supplierRepository,
                mailService, authenticationService);
    }

    @Test
    public void testUpdateOffer() {
        OfferDto dto = new OfferDto();
        dto.setOfferId(5l);
        dto.setPrice(100L);
        dto.setShippingDays(100L);
        Offer offer = subject.updateOffer(dto);

        assertTrue(true);
    }*/
}
