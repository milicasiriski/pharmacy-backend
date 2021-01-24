package rs.ac.uns.ftn.isa.pharmacy.demo.model.enums;

public enum MedicineForm {
    POWDER("Powder"),
    CAPSULE("Capsule"),
    TABLET("Tablet"),
    INHALER("Inhaler"),
    INJECTION("Injection"),
    DROPS("Drops"),
    GEL("Gel"),
    SYRUP("Syrup"),
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
