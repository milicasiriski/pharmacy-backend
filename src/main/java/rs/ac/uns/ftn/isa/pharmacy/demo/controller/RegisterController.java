package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ActivateDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPatientService;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    @Qualifier("registerPatientServiceImpl")
    private RegisterPatientService registerService;

    @Autowired
    public RegisterController(RegisterPatientService registerService) {
        this.registerService = registerService;
    }

    //@TODO: Check if values are ok
    @PostMapping("/patient")
    public ResponseEntity<String> register(HttpServletRequest request, @RequestBody PatientDTO patientDTO, UriComponentsBuilder ucBuilder) {
        Patient existUser = this.registerService.findByEmail(patientDTO.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }
        try {
            this.registerService.register(patientDTO,getSiteURL(request));
            return new ResponseEntity<>("/emailSent", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Registration failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody ActivateDto dto) {
        System.out.println(dto);
        String email = dto.getEmail();
        String code = dto.getCode();
        try {
            this.registerService.activate(email, code);
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
