package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserTokenState;
import rs.ac.uns.ftn.isa.pharmacy.demo.security.TokenUtils;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.UserService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControler {

    private TokenUtils tokenUtils;
    private CustomUserDetailsService userDetailsService;

    @Qualifier("userServiceImpl")
    private final UserService userService;

    @Autowired
    public UserControler(TokenUtils tokenUtils, CustomUserDetailsService userDetailsService, UserService userService) {
        this.userService = userService;
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        User user = (User) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();
            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }

    @GetMapping(value = "/getAllDermatologists")
    public ResponseEntity<List<DermatologistDto>> getAllDermatologists() {
        List<Dermatologist> dermatologists = userService.getAllDermatologists();
        List<DermatologistDto> dermatologistsDto = new ArrayList<>();

        System.out.println("APOTEKA0");

        dermatologists.forEach(dermatologist -> {
                    List<Pharmacy> pharmacies = dermatologist.getPharmacies();
                    List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();
                    pharmacies.forEach(pharmacy -> {
                        System.out.println("APOTEKA");
                        System.out.println(pharmacy);
                        PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress());
                        dtoPharmacies.add(pharmacyNameAndAddressDto);
                    });

                    DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.75, dtoPharmacies);
                    dermatologistsDto.add(dermatologistDto);
                }
        );

        return ResponseEntity.ok(dermatologistsDto);
    }
}
