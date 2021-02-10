package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rating {
    @Id
    @SequenceGenerator(name = "rating_sequence_generator", sequenceName = "rating_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_sequence_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "rating")
    private int stars;

    protected Rating() {
    }

    protected Rating(Long id, Patient patient, int stars) {
        this.id = id;
        this.patient = patient;
        this.stars = stars;
    }

    protected Rating(Patient patient, int stars) {
        this.patient = patient;
        this.stars = stars;
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

    public int getStars() {
        return stars;
    }

    public void setStars(int rating) {
        this.stars = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return stars == rating1.stars &&
                Objects.equals(id, rating1.id) &&
                Objects.equals(patient, rating1.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, stars);
    }
}
