package rs.ac.uns.ftn.isa.pharmacy.demo.model.enums;

public enum OfferStatus {
    WAITING("Waiting"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    public final String label;

    OfferStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
