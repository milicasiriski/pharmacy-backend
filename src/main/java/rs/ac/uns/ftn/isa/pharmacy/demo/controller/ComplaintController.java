package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ComplaintService;

import java.util.List;

@RestController
@RequestMapping(value = "/complaints", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/pharmacists")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<PharmacistDto>> getPharmacists() {
        try {
            return new ResponseEntity<>(complaintService.getPharmacists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dermatologists")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<DermatologistDto>> getDermatologists() {
        try {
            return new ResponseEntity<>(complaintService.getDermatologists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pharmacies")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<PharmacyDto>> getPharmacies() {
        try {
            return new ResponseEntity<>(complaintService.getPharmacies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
    public ResponseEntity<List<ComplaintDto>> getUnresolvedComplains() {
        try {
            return new ResponseEntity<>(complaintService.getUnresolvedComplains(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/answer")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
    public ResponseEntity<String> resolveComplaint(@RequestBody ComplaintAnswerDto dto) {
        try {
            complaintService.resolveComplaint(dto);
            return new ResponseEntity<>("Complaint resolved!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<String> makeAComplaint(@RequestBody ComplaintDto dto) {
        try {
            complaintService.makeAComplaint(dto);
            return new ResponseEntity<>("Your complaint is sent!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
