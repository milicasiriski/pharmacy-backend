package rs.ac.uns.ftn.isa.pharmacy.demo.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super("You sent bad request!");
    }
}
