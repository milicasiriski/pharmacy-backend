package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import java.util.Objects;

public class ExamPharmacist extends Exam {
    private Pharmacist pharmacist;

    public ExamPharmacist() {
        super();
    }

    public ExamPharmacist(int duration, double price, Pharmacist pharmacist) {
        super(duration, price);
        this.pharmacist = pharmacist;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExamPharmacist that = (ExamPharmacist) o;
        return Objects.equals(pharmacist, that.pharmacist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pharmacist);
    }

    @Override
    public String toString() {
        return "ExamPharmacist{" +
                "pharmacist=" + pharmacist +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
