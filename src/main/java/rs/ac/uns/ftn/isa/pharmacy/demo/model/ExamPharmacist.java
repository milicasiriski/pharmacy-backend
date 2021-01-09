package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam_pharmacist")
public class ExamPharmacist extends Exam {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    public ExamPharmacist() {
        super();
    }

    public ExamPharmacist(int duration, Pharmacist pharmacist) {
        super(duration);
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
                '}';
    }
}
