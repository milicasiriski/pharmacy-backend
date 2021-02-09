package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating_pharmacy")
public class RatingPharmacy extends Rating {
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    public RatingPharmacy() {
    }

    public RatingPharmacy(Long id, Patient patient, int rating, Pharmacy pharmacy) {
        super(id, patient, rating);
        this.pharmacy = pharmacy;
    }

    public RatingPharmacy(Patient patient, int rating, Pharmacy pharmacy) {
        super(patient, rating);
        this.pharmacy = pharmacy;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingPharmacy)) return false;
        if (!super.equals(o)) return false;
        RatingPharmacy that = (RatingPharmacy) o;
        return Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pharmacy);
    }
}
