package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LogInDto;

public interface RegisterUserService extends RegisterService<LogInDto, User>{

    User register(LogInDto dto);

}
