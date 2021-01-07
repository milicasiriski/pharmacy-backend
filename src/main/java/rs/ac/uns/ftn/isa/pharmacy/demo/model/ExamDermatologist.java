package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam_dermatologist")
public class ExamDermatologist extends Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true)
    private Long id;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Transient
    private Dermatologist dermatologist;

    public ExamDermatologist() {
        super();
    }

    public ExamDermatologist(int duration, double price, Dermatologist dermatologist) {
        super(duration, price);
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExamDermatologist that = (ExamDermatologist) o;
        return dermatologist.equals(that.dermatologist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dermatologist);
    }

    @Override
    public String toString() {
        return "ExamDermatologist{" +
                "dermatologist=" + dermatologist +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
