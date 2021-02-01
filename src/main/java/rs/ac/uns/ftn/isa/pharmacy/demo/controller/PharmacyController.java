package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyProfileDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/pharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PharmacyNameAndAddressDto>> getAllPharmaciesBasicInfo() {
        return ResponseEntity.ok(pharmacyService.findPharmaciesBasicInfo());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PharmacyDto>> getAllPharmacies() {
        List<PharmacyDto> pharmacies = pharmacyService.findAll();
        return ResponseEntity.ok(pharmacies);
    }

    @GetMapping("/getPharmacyById/{pharmacyId}")
    public ResponseEntity<PharmacyProfileDto> getPharmacyById(@PathVariable Long pharmacyId) {
        try {
            return ResponseEntity.ok(pharmacyService.findPharmacyById(pharmacyId));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //TODO:Vladimir, check if values are ok NOSONAR
    @PostMapping("/register")
    public ResponseEntity<String> registerPharmacy(@RequestBody PharmacyDto dto) {
        try {
            pharmacyService.save(dto);
            return ResponseEntity.ok("Pharmacy registered.");
        } catch (Exception e) {
            return new ResponseEntity<>("Pharmacy failed to register.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
