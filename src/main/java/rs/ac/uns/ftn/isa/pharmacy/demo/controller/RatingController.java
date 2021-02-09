package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RatingService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingController {

    @Qualifier("RatingServiceImpl")
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/dermatologists")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<List<DermatologistDto>> getDermatologists() {
        try {
            List<DermatologistDto> result = new ArrayList<>();
            ratingService.getDermatologists(getSignedInUser()).forEach(dermatologist -> {
                result.add(new DermatologistDto(dermatologist));
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pharmacists")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<List<PharmacistDto>> getPharmacists() {
        try {
            List<PharmacistDto> result = new ArrayList<>();
            ratingService.getPharmacists(getSignedInUser()).forEach(pharmacist -> {
                result.add(new PharmacistDto(pharmacist));
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
