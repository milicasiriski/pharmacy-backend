package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadPasswordException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.UserCredentialsService;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    @Qualifier("customUserDetailsService")
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserCredentialsServiceImpl(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) throws BadPasswordException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        User user = (User) userDetailsService.loadUserByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
