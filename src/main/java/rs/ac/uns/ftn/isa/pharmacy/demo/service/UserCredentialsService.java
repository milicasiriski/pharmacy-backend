package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadPasswordException;

public interface UserCredentialsService {

    void changePassword(String oldPassword, String newPassword) throws BadPasswordException;

}
