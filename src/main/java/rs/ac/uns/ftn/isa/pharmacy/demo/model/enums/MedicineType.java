package rs.ac.uns.ftn.isa.pharmacy.demo.model.enums;

public enum MedicineType {
    HUMAN("Human medicament"),
    HERBAL("Herbal medicine"),
    BIOPHARMACEUTICAL("Biopharmaceutical"),
    RADIOPHARMACEUTICAL("Radiopharmaceutical"),
    HOMEOPATHIC("Homeopathic remedy");


    public final String label;

    MedicineType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
