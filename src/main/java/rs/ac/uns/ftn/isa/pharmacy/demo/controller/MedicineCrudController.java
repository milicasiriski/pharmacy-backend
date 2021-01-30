package rs.ac.uns.ftn.isa.pharmacy.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LogInDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineNameUuidDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserTokenState;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineForm;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineType;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineCrudController {

    MedicineService medicineService;

    @Autowired
    public MedicineCrudController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    //TODO:Vladimir, check if values are ok
    @PostMapping("/save")
    public ResponseEntity<String> saveMedicine(@RequestBody MedicineDto dto) {
        try {
            medicineService.save(dto);
            return new ResponseEntity<>("Medicine saved!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sorry, you have sent a bad request!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/alternativeGroups")
    public ResponseEntity<List<List<MedicineNameUuidDto>>> getAlternativeGroups() {
        try {
            return new ResponseEntity<>(medicineService.getAlternativesGroups(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<MedicineDto>> getAllMedicine() {
        try {
            return new ResponseEntity<>(medicineService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
