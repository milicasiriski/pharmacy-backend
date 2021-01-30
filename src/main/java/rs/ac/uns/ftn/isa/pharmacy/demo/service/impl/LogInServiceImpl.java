package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LogInDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserTokenState;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.security.TokenUtils;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LogInService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.UserCredentialsService;


@Service
public class LogInServiceImpl implements LogInService {

    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserCredentialsService userCredentialsService;
    private final UserRepository userRepository;

    @Autowired
    public LogInServiceImpl(TokenUtils tokenUtils, AuthenticationManager authenticationManager, UserCredentialsService userCredentialsService, UserRepository userRepository) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userCredentialsService = userCredentialsService;
        this.userRepository = userRepository;
    }

    @Override
    public UserTokenState logIn(LogInDto authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String userType = user.getClass().getSimpleName();
        String accessToken = tokenUtils.generateToken(username);
        int accessExpiresIn = tokenUtils.getAccessTokenExpiresIn();
        String refreshToken = tokenUtils.generateRefreshToken(username);
        int refreshExpiresIn = tokenUtils.getRefreshTokenExpiresIn();
        return new UserTokenState(userType, accessToken, refreshToken, accessExpiresIn, refreshExpiresIn);
    }

    @Override
    public void firsLoginPasswordChange(String newPassword) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userCredentialsService.changePassword(user.getPassword(), newPassword);
        user.Enable();
        userRepository.save(user);
    }
}
