package rs.ac.uns.ftn.isa.pharmacy.demo.model.enums;

public enum DaysOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday");

    public final String label;

    DaysOfWeek(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
