package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters.DermatologistConverter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistShiftDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistEmploymentService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/dermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
public class DermatologistsController implements DermatologistConverter {

    private final DermatologistService dermatologistService;
    private final DermatologistEmploymentService dermatologistEmploymentService;

    @Autowired
    public DermatologistsController(DermatologistService dermatologistService, DermatologistEmploymentService dermatologistEmploymentService) {
        this.dermatologistService = dermatologistService;
        this.dermatologistEmploymentService = dermatologistEmploymentService;
    }

    @GetMapping(value = "/getDermatologistsByPharmacy/{pharmacyId}")
    public ResponseEntity<List<DermatologistDto>> getDermatologistsByPharmacy(@PathVariable Long pharmacyId) {
        List<Dermatologist> dermatologists = dermatologistService.getDermatologistsByPharmacy(pharmacyId);
        List<DermatologistDto> dermatologistsDto = createResponse(dermatologists);
        return ResponseEntity.ok(dermatologistsDto);
    }

    @GetMapping(value = "/getDermatologistsPharmacies")
    public ResponseEntity<List<PharmacyDto>> getDermatologistsPharmacies() {
        long dermatologistID = getSignedInUser().getId();
        List<PharmacyDto> pharmacies = dermatologistService.getDermatologistsPharmacies(dermatologistID);
        return ResponseEntity.ok(pharmacies);
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT','ROLE_PHARMACIST', 'ROLE_DERMATOLOGIST', 'ROLE_PHARMACY_ADMINISTRATOR', 'ROLE_SUPPLIER')") // NOSONAR the focus of this project is not on web security
    @GetMapping(value = "/getAllDermatologists")
    public ResponseEntity<List<DermatologistDto>> getAllDermatologists() {
        Iterable<Dermatologist> dermatologists = dermatologistService.getAllDermatologists();
        return ResponseEntity.ok(createResponse(dermatologists));
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @GetMapping(value = "/shiftIntervals/{dermatologistId}")
    public ResponseEntity<DermatologistShiftDto> getAllDermatologistShifts(@PathVariable Long dermatologistId) {
        return ResponseEntity.ok(dermatologistEmploymentService.getDermatologistShifts(dermatologistId));
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/getOtherDermatologists")
    public ResponseEntity<List<DermatologistDto>> getOtherDermatologists() {
        try {
            return new ResponseEntity<>(dermatologistService.getOtherDermatologists(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Dermatologist getSignedInUser() {
        return (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
