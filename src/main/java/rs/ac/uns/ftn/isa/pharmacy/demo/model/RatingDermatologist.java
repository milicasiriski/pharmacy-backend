package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating_dermatologist")
public class RatingDermatologist extends Rating {
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "dermatologist_id")
    private Dermatologist dermatologist;

    public RatingDermatologist() {
    }

    public RatingDermatologist(Long id, Patient patient, int rating, Dermatologist dermatologist) {
        super(id, patient, rating);
        this.dermatologist = dermatologist;
    }

    public RatingDermatologist(Patient patient, int rating, Dermatologist dermatologist) {
        super(patient, rating);
        this.dermatologist = dermatologist;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingDermatologist)) return false;
        if (!super.equals(o)) return false;
        RatingDermatologist that = (RatingDermatologist) o;
        return Objects.equals(dermatologist, that.dermatologist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dermatologist);
    }
}
