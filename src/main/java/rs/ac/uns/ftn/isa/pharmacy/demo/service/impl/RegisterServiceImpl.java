package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterService;
@Service
public class RegisterServiceImpl  implements RegisterService {
    private UserRepository userRepository;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) throws UnsupportedOperationException{
        return null;
    }
}
