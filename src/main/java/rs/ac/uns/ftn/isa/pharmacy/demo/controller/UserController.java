package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadPasswordException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PasswordDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserTokenState;
import rs.ac.uns.ftn.isa.pharmacy.demo.security.TokenUtils;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.UserService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final TokenUtils tokenUtils;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public UserController(TokenUtils tokenUtils, CustomUserDetailsService userDetailsService, UserService userService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        User user = (User) this.userDetailsService.loadUserByUsername(username);
        String userType = user.getClass().getSimpleName();
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();
            return ResponseEntity.ok(new UserTokenState(userType, refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_PHARMACY_ADMINISTRATOR','ROLE_SUPPLIER', 'ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<UserDto> getUserInfo() {
        return ResponseEntity.ok(userService.getUserInfo());
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_PHARMACY_ADMINISTRATOR','ROLE_SUPPLIER', 'ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<String> updateUserInfo(@RequestBody UserDto userDto) {
        try {
            userService.updateUserInfo(userDto);
            return new ResponseEntity<>("Info successfully updated!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasAnyRole('ROLE_PHARMACY_ADMINISTRATOR','ROLE_SUPPLIER', 'ROLE_PATIENT')") // NOSONAR
    public ResponseEntity<String> changePassword(@RequestBody PasswordDto passwordDto) {
        try {
            userService.updatePassword(passwordDto);
            return new ResponseEntity<>("Password successfully updated!", HttpStatus.OK);
        } catch (BadPasswordException e) {
            return new ResponseEntity<>("Wrong password, please try again!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }
}
