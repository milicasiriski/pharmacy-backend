package rs.ac.uns.ftn.isa.pharmacy.demo.model.enums;

public enum MedicineForm {
    TABLET("Tablet"),
    SYRUP("Syrup"),
    POWDER("Powder"),
    CAPSULE("Capsule"),
    INHALER("Inhaler"),
    INJECTION("Injection"),
    DROPS("Drops"),
    GEL("Gel"),
    PASTE("Paste");

    public final String label;

    MedicineForm(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
