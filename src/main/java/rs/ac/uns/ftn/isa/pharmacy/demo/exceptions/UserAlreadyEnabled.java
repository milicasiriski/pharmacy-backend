package rs.ac.uns.ftn.isa.pharmacy.demo.exceptions;

public class UserAlreadyEnabled extends RuntimeException{
    public UserAlreadyEnabled() {
        super("User is already activated!");
    }
}
