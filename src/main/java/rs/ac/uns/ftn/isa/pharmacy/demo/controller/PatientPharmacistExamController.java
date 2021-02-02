package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistExamSchedulingService;

@RestController
@RequestMapping(value = "/patient-exam", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientPharmacistExamController {
    private final PharmacistExamSchedulingService pharmacistExamSchedulingService;

    public PatientPharmacistExamController(PharmacistExamSchedulingService pharmacistExamSchedulingService) {
        this.pharmacistExamSchedulingService = pharmacistExamSchedulingService;
    }
}
