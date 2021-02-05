package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PasswordDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserDto;

public interface UserService {

    UserDto getUserInfo();

    void updatePassword(PasswordDto passwordDtoDto);

    void updateUserInfo(UserDto userDto);
}