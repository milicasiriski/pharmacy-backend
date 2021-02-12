package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PromotionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PromotionService;

import java.util.List;

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
            return new ResponseEntity<>("Promotion successfully added!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/subscribe/{id}")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<Boolean> updateSubscription(@PathVariable String id) {
        try {
            Long pharmacyId = Long.valueOf(id);
            Boolean subscribed = promotionService.updateSubscription(pharmacyId);
            return new ResponseEntity<>(subscribed, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/subscribe/{id}")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<Boolean> isSubscribed(@PathVariable String id) {
        try {
            Long pharmacyId = Long.valueOf(id);
            if (promotionService.isSubscribed(pharmacyId)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pharmacies")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<List<PharmacyDto>> getSubscribedPharmacies() {
        try {
            return new ResponseEntity<>(promotionService.getSubscribedPharmacies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
