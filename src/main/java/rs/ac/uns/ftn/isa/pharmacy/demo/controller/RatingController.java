package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PatientCannotRateThisEntity;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.RatingOutOfRangeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RatingService;

import javax.persistence.EntityNotFoundException;
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

    @GetMapping("/medicine")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<List<MedicineDto>> getMedicine() {
        try {
            List<MedicineDto> result = new ArrayList<>();
            ratingService.getMedicine().forEach(medicine -> {
                result.add(new MedicineDto(medicine));
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dermatologists")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<List<DermatologistDto>> getDermatologists() {
        try {
            List<DermatologistDto> result = new ArrayList<>();
            ratingService.getDermatologists().forEach(dermatologist -> {
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
            ratingService.getPharmacists().forEach(pharmacist -> {
                result.add(new PharmacistDto(pharmacist));
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pharmacies")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<List<PharmacyDto>> getPharmacies() {
        try {
            List<PharmacyDto> result = new ArrayList<>();
            ratingService.getPharmacies().forEach(pharmacy -> {
                result.add(new PharmacyDto(pharmacy));
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/medicine")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<String> submitMedicineRatings(@RequestBody List<SubmitRatingDto> ratings) {
        try {
            ratingService.saveMedicineRatings(ratings);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Entity does not exist.", HttpStatus.BAD_REQUEST);
        } catch (RatingOutOfRangeException e) {
            return new ResponseEntity<>("Rating is not valid.", HttpStatus.BAD_REQUEST);
        } catch (PatientCannotRateThisEntity e) {
            return new ResponseEntity<>("You are not authorized to rate this item.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Oops! Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dermatologists")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<String> submitDermatologistsRatings(@RequestBody List<SubmitRatingDto> ratings) {
        try {
            ratingService.saveDermatologistRatings(ratings);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Entity does not exist.", HttpStatus.BAD_REQUEST);
        } catch (RatingOutOfRangeException e) {
            return new ResponseEntity<>("Rating is not valid.", HttpStatus.BAD_REQUEST);
        } catch (PatientCannotRateThisEntity e) {
            return new ResponseEntity<>("You are not authorized to rate this item.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Oops! Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pharmacists")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<String> submitPharmacistsRatings(@RequestBody List<SubmitRatingDto> ratings) {
        try {
            ratingService.savePharmacistRatings(ratings);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Entity does not exist.", HttpStatus.BAD_REQUEST);
        } catch (RatingOutOfRangeException e) {
            return new ResponseEntity<>("Rating is not valid.", HttpStatus.BAD_REQUEST);
        } catch (PatientCannotRateThisEntity e) {
            return new ResponseEntity<>("You are not authorized to rate this item.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Oops! Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pharmacies")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<String> submitPharmaciesRatings(@RequestBody List<SubmitRatingDto> ratings) {
        try {
            ratingService.savePharmacyRatings(ratings);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Entity does not exist.", HttpStatus.BAD_REQUEST);
        } catch (RatingOutOfRangeException e) {
            return new ResponseEntity<>("Rating is not valid.", HttpStatus.BAD_REQUEST);
        } catch (PatientCannotRateThisEntity e) {
            return new ResponseEntity<>("You are not authorized to rate this item.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Oops! Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }
}
