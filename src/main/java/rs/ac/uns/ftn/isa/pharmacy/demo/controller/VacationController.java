package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters.VacationConverter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationRequestDermatologistDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationRequestPharmacistDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.VacationService;

@Controller
@RequestMapping(value = "/vacation", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationController implements VacationConverter {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacistVacation")
    public ResponseEntity<Iterable<VacationDto>> getPharmacistsVacation() {
        Iterable<VacationTimeRequestPharmacist> vacationTimeRequestPharmacists = vacationService.getAllPharmacistsVacation();
        return ResponseEntity.ok(createResponseForPharmacist(vacationTimeRequestPharmacists));
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/dermatologistVacation")
    public ResponseEntity<Iterable<VacationDto>> getDermatologistsVacation() {
        Iterable<VacationTimeRequestDermatologist> vacationTimeRequestDermatologists = vacationService.getAllDermatologistsVacation();
        return ResponseEntity.ok(createResponseForDermatologist(vacationTimeRequestDermatologists));
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/pharmacistVacation")
    public ResponseEntity<String> sendVacationResponsePharmacist(@RequestBody VacationDto vacationDto) {
        try {
            vacationService.sendVacationResponsePharmacist(vacationDto);
            return new ResponseEntity<>("Vacation response successfully send.", HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("Looks like someone already answered on this vacation request!", HttpStatus.I_AM_A_TEAPOT);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/dermatologistVacation")
    public ResponseEntity<String> sendVacationResponseDermatologist(@RequestBody VacationDto vacationDto) {
        try {
            vacationService.sendVacationResponseDermatologist(vacationDto);
            return new ResponseEntity<>("Vacation response successfully send.", HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("Looks like someone already answered on this vacation request!", HttpStatus.I_AM_A_TEAPOT);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/dermatologistVacationRequest")
    public ResponseEntity<String> sendVacationRequestDermatologist(@RequestBody VacationRequestDermatologistDTO vacationRequestDermatologistDTO) {
        try {
            vacationService.sendVacationRequestDermatologist(vacationRequestDermatologistDTO, getSignedInUserDermatologist());
            return new ResponseEntity<>("Vacation request successfully send.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/pharmacistVacationRequest")
    public ResponseEntity<String> sendVacationRequestPharmacist(@RequestBody VacationRequestPharmacistDTO vacationRequestPharmacistDTO) {
        try {
            vacationService.sendVacationRequestPharmacist(vacationRequestPharmacistDTO, getSignedInUserPharmacist());
            return new ResponseEntity<>("Vacation request successfully send.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    private Dermatologist getSignedInUserDermatologist() {
        return (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Pharmacist getSignedInUserPharmacist() {
        return (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}