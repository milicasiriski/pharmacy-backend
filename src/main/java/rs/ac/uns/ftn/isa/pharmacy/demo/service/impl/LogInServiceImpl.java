package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadPasswordException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.UserAlreadyEnabled;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Supplier;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.SystemAdmin;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LogInServiceImpl(TokenUtils tokenUtils, AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserTokenState logIn(LogInDto authenticationRequest) {
        return getUserTokenState(authenticationRequest);
    }

    @Override
    public UserTokenState firstLogInPasswordChange(LogInDto authenticationRequest) {
        User user = userRepository.findByEmail(authenticationRequest.getEmail());
        if (user.isEnabled()) {
            throw new UserAlreadyEnabled();
        }

        if (!passwordEncoder.matches(authenticationRequest.getOldPassword(), user.getPassword())) {
            throw new BadPasswordException();
        }
        if (isValidType(user)) {
            user.Enable();
            user.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
            userRepository.save(user);
            return getUserTokenState(authenticationRequest);
        } else {
            throw new BadUserInformationException();
        }
    }

    private boolean isValidType(User user) {
        return user.getClass() == SystemAdmin.class || user.getClass() == Supplier.class;
    }

    private UserTokenState getUserTokenState(LogInDto authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String userType = user.getClass().getSimpleName();
        String accessToken = tokenUtils.generateToken(username);
        int accessExpiresIn = tokenUtils.getExpiredIn();
        return new UserTokenState(userType, accessToken, accessExpiresIn);
    }
}
