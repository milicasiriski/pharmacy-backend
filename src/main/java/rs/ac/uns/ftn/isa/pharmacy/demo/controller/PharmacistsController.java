package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.DtoResponseConverters;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.UserService;

import java.util.List;

@Controller
public class PharmacistsController implements DtoResponseConverters {

    private final UserService userService;

    @Autowired
    public PharmacistsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getPharmacistsByPharmacy/{pharmacyName}")
    public ResponseEntity<List<PharmacistDto>> getPharmacistsByPharmacy(@PathVariable String pharmacyName) {
        List<Pharmacist> pharmacists = userService.getPharmacistsByPharmacy(pharmacyName);
        return ResponseEntity.ok(createPharmacistsResponseDtoFromPharmacists(pharmacists));
    }
}
