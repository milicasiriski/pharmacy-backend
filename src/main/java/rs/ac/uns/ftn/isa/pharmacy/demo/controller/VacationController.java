package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters.VacationConverter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.VacationService;

@Controller
@RequestMapping(value = "/vacation", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationController implements VacationConverter {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/pharmacistVacation")
    public ResponseEntity<Iterable<VacationDto>> getPharmacistsVacation() {
        Iterable<VacationTimeRequestPharmacist> vacationTimeRequestPharmacists = vacationService.getAllPharmacistsVacation();
        return ResponseEntity.ok(createResponseForPharmacist(vacationTimeRequestPharmacists));
    }

    @GetMapping("/dermatologistVacation")
    public ResponseEntity<Iterable<VacationDto>> getDermatologistsVacation() {
        Iterable<VacationTimeRequestDermatologist> vacationTimeRequestDermatologists = vacationService.getAllDermatologistsVacation();
        return ResponseEntity.ok(createResponseForDermatologist(vacationTimeRequestDermatologists));
    }

    @PostMapping("/pharmacistVacation")
    public ResponseEntity<Void> sendVacationResponsePharmacist(@RequestBody VacationDto vacationDto) {
        try {
            vacationService.sendVacationResponsePharmacist(vacationDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dermatologistVacation")
    public ResponseEntity<Void> sendVacationResponseDermatologist(@RequestBody VacationDto vacationDto) {
        try {
            vacationService.sendVacationResponseDermatologist(vacationDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}