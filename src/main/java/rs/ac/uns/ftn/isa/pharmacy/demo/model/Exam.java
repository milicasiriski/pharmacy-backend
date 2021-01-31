package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam")
@Inheritance(strategy = InheritanceType.JOINED)
public class Exam {

    @Id
    @SequenceGenerator(name = "exam_sequence_generator", sequenceName = "exam_sequence", initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_sequence_generator")
    private Long id;

    @Column(name = "price")
    private double price;

    @Embedded
    @Column(name = "timeinterval")
    private TimeInterval timeInterval;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Exam() {
    }

    public Exam(double price, TimeInterval timeInterval) {
        this.price = price;
        this.timeInterval = timeInterval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TimeInterval getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(TimeInterval timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isScheduled() {
        return getPatient() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Double.compare(exam.price, price) == 0 &&
                Objects.equals(id, exam.id) &&
                Objects.equals(timeInterval, exam.timeInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, timeInterval);
    }
}
