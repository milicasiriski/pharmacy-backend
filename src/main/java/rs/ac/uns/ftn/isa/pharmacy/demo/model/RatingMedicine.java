package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating_medicine")
public class RatingMedicine extends Rating {
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public RatingMedicine() {
    }

    public RatingMedicine(Long id, Patient patient, int rating, Medicine medicine) {
        super(id, patient, rating);
        this.medicine = medicine;
    }

    public RatingMedicine(Patient patient, int rating, Medicine medicine) {
        super(patient, rating);
        this.medicine = medicine;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingMedicine)) return false;
        if (!super.equals(o)) return false;
        RatingMedicine that = (RatingMedicine) o;
        return Objects.equals(medicine, that.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), medicine);
    }
}
