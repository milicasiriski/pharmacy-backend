package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OfferService;

@RestController
@RequestMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    public ResponseEntity<String> saveOffer(OfferDto OfferDto){
        try {
            this.offerService.addNewOffer(OfferDto);
            return new ResponseEntity<>("Offer saved successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Sorry, there has been a mistake on server.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
