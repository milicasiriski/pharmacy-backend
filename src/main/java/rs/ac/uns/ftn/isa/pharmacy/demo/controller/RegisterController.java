package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterService;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    @Qualifier("registerPatientServiceImpl")
    private RegisterService<PatientDTO, Patient> registerService;

    @Autowired
    public RegisterController(RegisterService<PatientDTO, Patient> registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/patient")
    public ResponseEntity<Patient> register(@RequestBody PatientDTO patientDTO, UriComponentsBuilder ucBuilder) {
        Patient existUser = this.registerService.findByEmail(patientDTO.getEmail());
        if (existUser != null) {
            throw new RuntimeException("Email already exists");
        }
        Patient user = this.registerService.save(patientDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
