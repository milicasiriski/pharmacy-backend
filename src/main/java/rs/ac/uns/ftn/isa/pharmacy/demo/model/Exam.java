package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "exam")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Exam implements Serializable {
    @Id
    @SequenceGenerator(name = "exam_sequence_generator", sequenceName = "exam_sequence", initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_sequence_generator")
    private Long id;

    @Column(name = "duration")
    protected int duration;

    protected Exam() {
    }

    protected Exam(int duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return duration == exam.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration);
    }

}
