package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/intialPasswordChange")
    public ResponseEntity<String> firstTimeLogin(@RequestBody String newPassword) {
        try {
            logInService.firsLoginPasswordChange(newPassword);
            return new ResponseEntity<>("Registration succeeded!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Password change failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
