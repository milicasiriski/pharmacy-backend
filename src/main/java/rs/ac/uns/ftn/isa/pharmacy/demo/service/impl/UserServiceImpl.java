package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadPasswordException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.AddressDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PasswordDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Address address = user.getAddress();
        AddressDto addressDto = new AddressDto(address);

        return new UserDto(addressDto, user.getName(), user.getSurname());
    }

    @Override
    public void updateUserInfo(UserDto userDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AddressDto addressDto = userDto.getAddress();

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAddress(new Address(addressDto.getCountry(), addressDto.getStreet(), addressDto.getStreet()));

        userRepository.save(user);
    }

    @Override
    public void updatePassword(PasswordDto passwordDto) throws BadPasswordException, BadUserInformationException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(user.getPassword());

        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            throw new BadPasswordException();
        }
        if (isValidType(user)) {
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new BadUserInformationException();
        }
    }

    private boolean isValidType(User user) {
        return user.getClass() == SystemAdmin.class || user.getClass() == Supplier.class || user.getClass() == PharmacyAdmin.class;
    }
}
