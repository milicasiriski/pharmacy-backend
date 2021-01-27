package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NotAPatientException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ActivateDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserRegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPatientService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    @Qualifier("registerPatientServiceImpl")
    private RegisterPatientService registerPatientService;

    @Qualifier("registerUserServiceImpl")
    private RegisterUserService registerUserService;

    @Autowired
    public RegisterController(RegisterUserService registerUserService, RegisterPatientService registerPatientService) {
        this.registerPatientService = registerPatientService;
        this.registerUserService = registerUserService;
    }

    //@TODO: Check if values are ok
    @PostMapping("/patient")
    public ResponseEntity<String> registerPatient(HttpServletRequest request, @RequestBody PatientDto patientDTO, UriComponentsBuilder ucBuilder) {
        Patient existUser;
        try {
            existUser = this.registerPatientService.findByEmail(patientDTO.getEmail());
        }
        catch (NotAPatientException e){
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }

        if (existUser != null) {
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }
        try {
            this.registerPatientService.register(patientDTO, getSiteURL(request));
            return new ResponseEntity<>("/emailSent", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Registration failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> registerSystemAdmin(@RequestBody UserRegistrationDto adminCredentials, UriComponentsBuilder ucBuilder) {
        User existUser = this.registerUserService.findByEmail(adminCredentials.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }
        try {
            this.registerUserService.register(adminCredentials);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Registration failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody ActivateDto dto) {
        String email = dto.getEmail();
        String code = dto.getCode();
        try {
            this.registerPatientService.activate(email, code);
            return new ResponseEntity<>("/activation/success", HttpStatus.OK);
        } catch (BadActivationCodeException e) {
            e.printStackTrace();
            return new ResponseEntity<>("/activation/failed", HttpStatus.BAD_REQUEST);
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        //String siteURL = request.getRequestURL().toString();
        //return siteURL.replace(request.getServletPath(), "");
        return "http://localhost:8081";
    }

}
