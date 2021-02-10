package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;

public interface AuthenticationService {

    User getLoggedUser(); // USING THIS FOR MOCKING LOGGED USER IN TESTS
}
