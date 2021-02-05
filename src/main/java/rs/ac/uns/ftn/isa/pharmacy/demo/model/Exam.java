package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.ExamStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.Constants;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SqlResultSetMapping(
        name = "ExamDetailsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ExamDetails.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "price", type = Double.class),
                                @ColumnResult(name = "end_time", type = Calendar.class),
                                @ColumnResult(name = "start_time", type = Calendar.class),
                                @ColumnResult(name = "dermatologist_name", type = String.class),
                                @ColumnResult(name = "dermatologist_surname", type = String.class),
                                @ColumnResult(name = "pharmacy_name", type = String.class)
                        }
                )
        }
)
@NamedNativeQuery(name = "Exam.getExamDetails", query = "SELECT e.id AS id, price, end_time, start_time, pu.name AS dermatologist_name, pu.surname AS dermatologist_surname, ph.name AS pharmacy_name\n" +
        "\tFROM public.exam AS e, public.dermatologist_employment_mapping AS dem, public.pharmacy as ph, public.pharmacy_user as pu\n" +
        "\tWHERE employment_id = dermatologist_employment_id AND dem.pharmacy_id = ph.id AND dem.dermatologist_id = pu.id\n" +
        "\tAND e.patient_id = :patientId", resultSetMapping = "ExamDetailsMapping")
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

    @Column
    private ExamStatus status;

    public Exam() {
    }

    public Exam(double price, TimeInterval timeInterval, ExamStatus status) {
        this.price = price;
        this.timeInterval = timeInterval;
        this.status = status;
    }

    public Exam(double price, TimeInterval timeInterval, Patient patient) {
        this.price = price;
        this.timeInterval = timeInterval;
        this.patient = patient;
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

    public boolean isCancellable() {
        Calendar now = Calendar.getInstance();
        long differenceInMilliseconds = timeInterval.getStart().getTime().getTime() - now.getTime().getTime();
        long differenceInHours = TimeUnit.HOURS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);

        return differenceInHours >= Constants.DERMATOLOGIST_EXAM_CANCELLATION_HOURS;
    }

    public void cancel() {
        this.patient = null;
    }

    public ExamStatus getStatus() {
        return status;
    }

    public void setStatus(ExamStatus status) {
        this.status = status;
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
