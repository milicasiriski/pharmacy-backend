package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exam")
@Inheritance(strategy = InheritanceType.JOINED)
public class Exam implements Serializable {

    @Id
    @SequenceGenerator(name = "exam_sequence_generator", sequenceName = "exam_sequence", initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_sequence_generator")
    private Long id;

    @Column(name = "price")
    private double price;

    @Embedded
    @Column(name = "timeinterval")
    private TimeInterval timeInterval;

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
}
