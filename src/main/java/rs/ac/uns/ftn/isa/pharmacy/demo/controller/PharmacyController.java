package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;
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

    @PostMapping("/register")
    public ResponseEntity<String> registerPharmacy(PharmacyDto dto) {
        try{
            Pharmacy pharmacy = pharmacyService.save(dto);
        }
        catch (Exception e){
            return new ResponseEntity<>("Pharmacy register.", HttpStatus.OK);
        }
        return ResponseEntity.ok("Pharmacy register.");
    }
}
