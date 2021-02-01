package rs.ac.uns.ftn.isa.pharmacy.demo.exceptions;

public class NoMedicineFoundException extends RuntimeException {
    public NoMedicineFoundException() {
        super("No medicine was found");
    }
}
