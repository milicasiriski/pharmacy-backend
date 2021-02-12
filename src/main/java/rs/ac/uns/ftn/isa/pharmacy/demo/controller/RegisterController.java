package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PharmacyMissingException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ActivateDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserRegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPatientService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPharmacistService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    @Qualifier("registerPatientServiceImpl")
    private RegisterPatientService registerPatientService;

    @Qualifier("registerUserServiceImpl")
    private RegisterUserService registerUserService;

    private final RegisterPharmacistService registerPharmacistService;

    private final static String userExistsAlert = "User with that mail address already exists!";
    private final static String registrationFailedAlert = "Registration failed!";
    private final static String missingBasicUserInfoAlert = "Registration failed! Missing name, email or password";

    @Autowired
    public RegisterController(RegisterUserService registerUserService, RegisterPatientService registerPatientService, RegisterPharmacistService registerPharmacistService) {
        this.registerPatientService = registerPatientService;
        this.registerUserService = registerUserService;
        this.registerPharmacistService = registerPharmacistService;
    }

    @PostMapping("/patient")
    public ResponseEntity<String> registerPatient(HttpServletRequest request, @RequestBody PatientDto patientDTO) {
        if (!validUserInfo(patientDTO.getName(), patientDTO.getEmail(), patientDTO.getPassword())) {
            return new ResponseEntity<>(missingBasicUserInfoAlert, HttpStatus.BAD_REQUEST);
        }
        if (this.registerPatientService.userExists(patientDTO.getEmail())) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        }
        try {
            this.registerPatientService.register(patientDTO, getSiteURL(request));
            return new ResponseEntity<>("/emailSent", HttpStatus.OK);
        } catch (BadUserInformationException e) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(registrationFailedAlert, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pharmacist")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> registerPharmacist(@RequestBody UserRegistrationDto credentials) {
        if (registerUserService.userExists(credentials.getEmail())) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        }
        try {
            this.registerPharmacistService.register(credentials);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(registrationFailedAlert, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validUserInfo(String name, String email, String password) {
        return name != null && !name.isEmpty() && email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")// NOSONAR the focus of this project is not on web security
    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto credentials) {
        if (!validUserInfo(credentials.getName(), credentials.getEmail(), credentials.getPassword())) {
            return new ResponseEntity<>(missingBasicUserInfoAlert, HttpStatus.BAD_REQUEST);
        }
        if (registerUserService.userExists(credentials.getEmail())) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        }
        try {
            this.registerUserService.register(credentials);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        } catch (BadUserInformationException | DataIntegrityViolationException e) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        } catch (PharmacyMissingException e) {
            return new ResponseEntity<>("If you add new pharmacy admin, you must set his (hers) pharmacy.",
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(registrationFailedAlert, HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>("/activation/failed", HttpStatus.BAD_REQUEST);
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        return request.getHeader("origin");
    }

}
