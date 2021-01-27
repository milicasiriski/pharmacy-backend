package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LogInDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.UserType;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthorityService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterUserService;


import java.util.List;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private UserRepository userRepository;
    private AuthorityService authService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterUserServiceImpl(UserRepository userRepository,
                                   AuthorityService authService,
                                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public User register(LogInDto dto) {
        User user;
        UserType type = dto.getType();
        user = createUserByType(type);
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        List<Authority> auth = authService.findByname(user.getAdministrationRole());
        user.setAuthorities(auth);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    private User createUserByType(UserType type) throws BadUserInformationException {
        User user;
        switch (type){
            case PATIENT:
                user = new Patient();
                break;
            case PHARMACIST:
                user = new Pharmacist();
                break;
            case SYSTEM_ADMIN:
                user = new SystemAdmin();
                break;
            case DERMATOLOGIST:
                user = new Dermatologist();
                break;
            case PHARMACY_ADMIN:
                user = new PharmacyAdmin();
                break;
            case SUPPLIER:
                user = new Supplier();
                break;
            default:
                throw new BadUserInformationException();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
