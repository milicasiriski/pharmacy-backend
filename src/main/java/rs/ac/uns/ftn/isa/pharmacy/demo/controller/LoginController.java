package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LogInDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserTokenState;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LogInService;

@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private LogInService logInService;

    @Autowired
    public LoginController(LogInService logInService) {
        this.logInService = logInService;
    }

    @PostMapping("/")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody LogInDto authenticationRequest) {
        UserTokenState state = logInService.logIn(authenticationRequest);
        return ResponseEntity.ok(state);
    }

    @PostMapping("/initialPasswordChange")
    public ResponseEntity<UserTokenState> firstTimeLogin(@RequestBody LogInDto authenticationRequest) {
        try {
            return new ResponseEntity<>(logInService.firstLogInPasswordChange(authenticationRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
