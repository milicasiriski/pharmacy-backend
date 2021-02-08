package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.DermatologistHasExamException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.DermatologistHasShiftInAnotherPharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.MedicineHasReservationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PharmacistHasExamException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.AddDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyProfileDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.RatingFilter;

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

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_UNAUTHORISED', 'ROLE_SYSTEM_ADMINISTRATOR')") // NOSONAR
    @GetMapping("/{ratingFilter}/{distance}/{userLon}/{userLat}")
    public ResponseEntity<List<PharmacyDto>> getAllPharmacies(@PathVariable("ratingFilter") RatingFilter ratingFilter,
                                                              @PathVariable("distance") double distance,
                                                              @PathVariable("userLon") double userLon,
                                                              @PathVariable("userLat") double userLat) {
        List<PharmacyDto> pharmacies = pharmacyService.findAll(ratingFilter, distance, userLon, userLat);
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
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")// NOSONAR the focus of this project is not on web security
    @PostMapping("/register")
    public ResponseEntity<String> registerPharmacy(@RequestBody PharmacyDto dto) {
        try {
            pharmacyService.save(dto);
            return ResponseEntity.ok("Pharmacy registered.");
        } catch (Exception e) {
            return new ResponseEntity<>("Pharmacy failed to register.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPharmacyByAdmin")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<PharmacyDto> getPharmacyByPharmacyAdmin() {
        return new ResponseEntity<>(pharmacyService.getPharmacyInfoByAdmin(), HttpStatus.OK);
    }

    @PostMapping("/updatePharmacy")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> updatePharmacyInfo(@RequestBody PharmacyDto pharmacyDto) {
        pharmacyService.updatePharmacyInfo(pharmacyDto);
        return ResponseEntity.ok("Pharmacy updated successfully.");
    }


    @PutMapping("/addMedicine/{medicineId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> addMedicine(@PathVariable Long medicineId) {
        try {
            pharmacyService.addMedicine(medicineId);
            return new ResponseEntity<>("Medicine successfully added!", HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>("There is no such medicine in system!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteMedicine/{medicineId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> deleteMedicine(@PathVariable Long medicineId) {
        try {
            pharmacyService.removeMedicine(medicineId);
            return new ResponseEntity<>("Medicine successfully deleted!", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Medicine does not exist!", HttpStatus.BAD_REQUEST);
        } catch (MedicineHasReservationException e) {
            return new ResponseEntity<>("Medicine has reservation!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletePharmacist/{pharmacistId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> deletePharmacist(@PathVariable Long pharmacistId) {
        try {
            pharmacyService.removePharmacist(pharmacistId);
            return new ResponseEntity<>("Pharmacist removed successfully!", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Pharmacist does not exist!", HttpStatus.BAD_REQUEST);
        } catch (PharmacistHasExamException e) {
            return new ResponseEntity<>("Pharmacist has exam!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteDermatologist/{dermatologistId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> deleteDermatologist(@PathVariable Long dermatologistId) {
        try {
            pharmacyService.removeDermatologist(dermatologistId);
            return new ResponseEntity<>("Dermatologist removed successfully!", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Dermatologist does not exist!", HttpStatus.BAD_REQUEST);
        } catch (DermatologistHasExamException e) {
            return new ResponseEntity<>("Dermatologist has exam!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addDermatologist/")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> addDermatologist(@RequestBody AddDermatologistDto addDermatologistDto) {
        try {
            pharmacyService.addDermatologist(addDermatologistDto);
            return ResponseEntity.ok("Dermatologist successfully added!");
        } catch (DermatologistHasShiftInAnotherPharmacy e) {
            return new ResponseEntity<>("Dermatologist has shift in another pharmacy!", HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("There is no such dermatologist in system!", HttpStatus.BAD_REQUEST);
        }
    }
}
