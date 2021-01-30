package rs.ac.uns.ftn.isa.pharmacy.demo.exceptions;

public class MedicineReservationCannotBeCancelledException extends RuntimeException {
    public MedicineReservationCannotBeCancelledException() {
        super("Medicine reservation cannot be cancelled!");
    }
}
