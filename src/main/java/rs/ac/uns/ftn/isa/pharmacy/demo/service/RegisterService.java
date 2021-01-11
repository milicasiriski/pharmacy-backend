package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;

public interface RegisterService {
    User save(User user);
}
