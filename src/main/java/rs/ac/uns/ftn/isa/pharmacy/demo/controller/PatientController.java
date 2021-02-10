package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PatientService;

import java.util.List;

@Controller
@RequestMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
    @Qualifier("PatientServiceImpl")
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/allergies")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<Iterable<MedicinesBasicInfoDto>> getAllAllergies() {
        try {
            return new ResponseEntity<>(patientService.getAllAllergies(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/medicine")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<Iterable<MedicinesBasicInfoDto>> getAllMedicine() {
        try {
            return new ResponseEntity<>(patientService.getAllMedicine(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
