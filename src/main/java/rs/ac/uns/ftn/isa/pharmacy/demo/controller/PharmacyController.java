package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyProfileDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.PharmacyServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/pharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {

    private final PharmacyServiceImpl pharmacyService;

    @Autowired
    public PharmacyController(PharmacyServiceImpl pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PharmacyNameAndAddressDto>> getOrders() {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();
        pharmacies.forEach(pharmacy -> dtoPharmacies.add(new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress())));
        return ResponseEntity.ok(dtoPharmacies);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PharmacyDto>> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<PharmacyDto> dtoPharmacies = new ArrayList<>();
        pharmacies.forEach(pharmacy -> dtoPharmacies.add(new PharmacyDto(pharmacy.getName(), pharmacy.getAddress(), pharmacy.getAbout(), pharmacy.getId(), pharmacy.getRating())));
        return ResponseEntity.ok(dtoPharmacies);
    }

    @GetMapping("/getPharmacyById/{pharmacyId}")
    public ResponseEntity<PharmacyProfileDto> getPharmacyById(@PathVariable Long pharmacyId) {
        return ResponseEntity.ok(pharmacyService.findPharmacyById(pharmacyId));
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
