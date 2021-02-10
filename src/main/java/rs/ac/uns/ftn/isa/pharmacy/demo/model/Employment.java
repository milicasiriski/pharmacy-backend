package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "employment")
public class Employment {

    @Id
    @SequenceGenerator(name = "employment_sequence_generator", sequenceName = "employment_sequence", initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employment_sequence_generator")
    private Long id;

    @Version
    private Long version;

    @ElementCollection
    @CollectionTable(name = "shift_day_mapping",
            joinColumns = {@JoinColumn(name = "employment_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "day_of_week")
    private Map<DaysOfWeek, TimeInterval> shifts;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration")
    private Integer duration;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employment_id")
    private List<Exam> exams;

    public Employment() {

    }

    public Employment(Map<DaysOfWeek, TimeInterval> shifts, Double price, Integer duration, List<Exam> exams) {
        this.shifts = shifts;
        this.price = price;
        this.duration = duration;
        this.exams = exams;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<DaysOfWeek, TimeInterval> getShifts() {
        return shifts;
    }

    public void setShifts(Map<DaysOfWeek, TimeInterval> shifts) {
        this.shifts = shifts;
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
        Employment that = (Employment) o;
        return duration == that.duration &&
                Objects.equals(id, that.id) &&
                Objects.equals(shifts, that.shifts) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shifts, price, duration);
    }
}
