package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.DtoResponseConverters;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
public class DermatologistsController {

    private final UserService userService;

    @Autowired
    public DermatologistsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getDermatologistsByPharmacy/{pharmacyName}")
    public ResponseEntity<List<DermatologistDto>> getAllDermatologists(@PathVariable String pharmacyName) {
        List<Dermatologist> dermatologists = userService.getDermatologistsByPharmacy(pharmacyName);
        List<DermatologistDto> dermatologistsDto = new ArrayList<>();

        DtoResponseConverters.createDermatologistsResponseDtoFromDermatologists(dermatologists, dermatologistsDto);
        return ResponseEntity.ok(dermatologistsDto);
    }
}
