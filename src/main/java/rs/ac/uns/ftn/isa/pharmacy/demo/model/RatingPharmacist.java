package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating_pharmacist")
public class RatingPharmacist extends Rating {
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    public RatingPharmacist() {
    }

    public RatingPharmacist(Long id, Patient patient, int rating, Pharmacist pharmacist) {
        super(id, patient, rating);
        this.pharmacist = pharmacist;
    }

    public RatingPharmacist(Patient patient, int rating, Pharmacist pharmacist) {
        super(patient, rating);
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
        if (!(o instanceof RatingPharmacist)) return false;
        if (!super.equals(o)) return false;
        RatingPharmacist that = (RatingPharmacist) o;
        return Objects.equals(pharmacist, that.pharmacist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pharmacist);
    }
}
