package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.GetPharmaciesForPharmacistExamResponse;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistExamSchedulingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/pharmacistExam", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientPharmacistExamController {
    private final PharmacistExamSchedulingService pharmacistExamSchedulingService;

    public PatientPharmacistExamController(PharmacistExamSchedulingService pharmacistExamSchedulingService) {
        this.pharmacistExamSchedulingService = pharmacistExamSchedulingService;
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacies/{dateTime}")
    public ResponseEntity<Iterable<GetPharmaciesForPharmacistExamResponse>> getPharmaciesWithAvailablePharmacists(@PathVariable("dateTime") Date dateTime) {
        try {
            List<GetPharmaciesForPharmacistExamResponse> response = new ArrayList<>();
            pharmacistExamSchedulingService.getPharmaciesWithAvailableAppointments(dateTime).forEach(pharmacy ->
                    response.add(new GetPharmaciesForPharmacistExamResponse(pharmacy))
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
