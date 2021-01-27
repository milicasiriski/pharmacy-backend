package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PromotionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PromotionService;

@Controller
@RequestMapping(value = "/promotion", produces = MediaType.APPLICATION_JSON_VALUE)
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> saveNewPromotion(@RequestBody PromotionDto promotionDto) {
        try {
            promotionService.addNewPromotion(promotionDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
