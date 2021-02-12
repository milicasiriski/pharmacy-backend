package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LoyaltyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.LoyaltyProgramStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
    @Qualifier("PatientServiceImpl")
    private final PatientService patientService;
    @Qualifier("LoyaltyServiceImpl")
    private final LoyaltyService loyaltyService;

    @Autowired
    public PatientController(PatientService patientService, LoyaltyService loyaltyService) {
        this.patientService = patientService;
        this.loyaltyService = loyaltyService;
    }

    @GetMapping("/allergies")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<Iterable<MedicinesBasicInfoDto>> getAllAllergies() {
        try {
            return new ResponseEntity<>(patientService.getAllAllergies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/medicine")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<Iterable<MedicinesBasicInfoDto>> getAllMedicine() {
        try {
            return new ResponseEntity<>(patientService.getAllMedicine(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/allergies")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<Iterable<MedicineDto>> updateAllergies(@RequestBody List<MedicinesBasicInfoDto> medicine) {
        try {
            patientService.updateAllergies(medicine);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/prescription")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<Iterable<EPrescriptionDto>> getPrescriptions() {
        try {
            List<EPrescriptionDto> result = new ArrayList<>();
            patientService.getAllPrescriptions().forEach(prescription -> {
                result.add(new EPrescriptionDto(prescription));
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/penaltyPoints")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<Integer> getPenaltyPoints() {
        try {
            Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new ResponseEntity<>(patient.getPenaltyPoints(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/loyaltyProgram")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<PatientLoyaltyPointsDto> getLoyaltyProgram() {
        try {
            Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            LoyaltyProgramDto loyaltyProgram = loyaltyService.getLoyaltyProgramDto();

            LoyaltyProgramStatus status = LoyaltyProgramStatus.REGULAR;
            double discount = 0;
            if (patient.getLoyaltyPoints() >= loyaltyProgram.getGoldMinimumPoints()) {
                status = LoyaltyProgramStatus.GOLD;
                discount = loyaltyProgram.getGoldDiscount();
            } else if (patient.getLoyaltyPoints() >= loyaltyProgram.getSilverMinimumPoints()) {
                status = LoyaltyProgramStatus.SILVER;
                discount = loyaltyProgram.getSilverDiscount();
            }

            PatientLoyaltyPointsDto result = new PatientLoyaltyPointsDto(status, patient.getLoyaltyPoints(), discount);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Scheduled(cron = "${penaltyPointReset.cron}")
    public void resetPenaltyPoints() {
        patientService.resetPenaltyPoints();
    }
}
