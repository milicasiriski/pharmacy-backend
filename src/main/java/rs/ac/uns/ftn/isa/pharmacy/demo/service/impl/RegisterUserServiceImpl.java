package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserRegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.UserType;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthorityService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterUserService;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private final UserRepository userRepository;
    private final AuthorityService authService;
    private final PasswordEncoder passwordEncoder;
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public RegisterUserServiceImpl(UserRepository userRepository,
                                   AuthorityService authService,
                                   PasswordEncoder passwordEncoder,
                                   PharmacyRepository pharmacyRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.pharmacyRepository = pharmacyRepository;

    }

    @Override
    public User register(UserRegistrationDto dto) {
        User user;
        UserType type = dto.getType();
        user = createUserByType(type);
        //TODO:Vladimir, this will be potential transaction NOSONAR
        if (user.getClass() == PharmacyAdmin.class) {
            Optional<Pharmacy> pharmacy = pharmacyRepository.findById(dto.getPharmacyId());
            if (pharmacy.isEmpty()) {
                throw new BadUserInformationException();
            }
            ((PharmacyAdmin) user).setPharmacy(pharmacy.get());
        }
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setSurname(dto.getSurname());
        List<Authority> auth = authService.findByname(user.getAdministrationRole());
        user.setAuthorities(auth);
        user = userRepository.save(user);
        return user;
    }

    private User createUserByType(UserType type) throws BadUserInformationException {
        User user;
        switch (type) {
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

    @Override
    public boolean userExists(String email) {
        return findByEmail(email) != null;
    }

}
