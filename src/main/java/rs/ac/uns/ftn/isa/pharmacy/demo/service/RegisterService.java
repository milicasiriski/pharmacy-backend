package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;

import java.util.List;

public interface RegisterService<DTO, USER>{
    USER save(DTO dto);
    USER findByEmail(String email);
}
