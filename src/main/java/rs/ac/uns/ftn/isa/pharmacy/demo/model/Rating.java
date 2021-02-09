package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rating {
    @Id
    @SequenceGenerator(name = "rating_sequence_generator", sequenceName = "rating_sequence", initialValue = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_sequence_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "rating")
    private int rating;

    public Rating() {
    }

    public Rating(Long id, Patient patient, int rating) {
        this.id = id;
        this.patient = patient;
        this.rating = rating;
    }

    public Rating(Patient patient, int rating) {
        this.patient = patient;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return rating == rating1.rating &&
                Objects.equals(id, rating1.id) &&
                Objects.equals(patient, rating1.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, rating);
    }
}
