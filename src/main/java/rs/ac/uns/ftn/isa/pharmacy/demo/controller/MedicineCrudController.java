package rs.ac.uns.ftn.isa.pharmacy.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineNameUuidDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineService;

import java.util.List;

@RestController
@RequestMapping(value = "/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineCrudController {

    MedicineService medicineService;

    @Autowired
    public MedicineCrudController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    //TODO:Vladimir, check if values are ok NOSONAR
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")// NOSONAR the focus of this project is not on web security
    @PostMapping("/save")
    public ResponseEntity<String> saveMedicine(@RequestBody MedicineDto dto) {
        try {
            medicineService.save(dto);
            return new ResponseEntity<>("Medicine saved!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Sorry, you have sent a bad request!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/alternativeGroups")
    public ResponseEntity<List<List<MedicineNameUuidDto>>> getAlternativeGroups() {
        try {
            return new ResponseEntity<>(medicineService.getAlternativesGroups(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<MedicineDto>> getAllMedicine() {
        try {
            return new ResponseEntity<>(medicineService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
