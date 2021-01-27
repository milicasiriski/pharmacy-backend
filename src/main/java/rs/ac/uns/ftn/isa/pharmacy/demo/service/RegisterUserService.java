package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserRegistrationDto;

public interface RegisterUserService extends RegisterService<UserRegistrationDto, User>{

    User register(UserRegistrationDto dto);

}
