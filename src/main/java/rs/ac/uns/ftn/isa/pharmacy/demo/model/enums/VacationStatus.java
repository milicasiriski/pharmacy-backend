package rs.ac.uns.ftn.isa.pharmacy.demo.model.enums;

public enum VacationStatus {
    REJECTED("Rejected"),
    APPROVED("Approved"),
    WAITING("Waiting");

    public final String label;

    VacationStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
