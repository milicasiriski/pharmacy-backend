package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.VacationStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacation_time_request")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class VacationTimeRequest {

    @Id
    @SequenceGenerator(name = "vacation_time_request_sequence_generator", sequenceName = "vacation_time_request_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacation_time_request_sequence_generator")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "time_start")),
            @AttributeOverride(name = "end", column = @Column(name = "time_end"))
    })
    protected TimeInterval requestedTimeForVacation;

    @Column(name = "status")
    protected VacationStatus status;

    @Column(name = "rejected_reason")
    protected String rejectedReason;

    protected VacationTimeRequest() {

    }

    protected VacationTimeRequest(TimeInterval requestedTimeForVacation, VacationStatus status, String rejectedReason) {
        this.requestedTimeForVacation = requestedTimeForVacation;
        this.status = status;
        this.rejectedReason = rejectedReason;
    }

    public VacationStatus getStatus() {
        return status;
    }

    public void setStatus(VacationStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeInterval getRequestedTimeForVacation() {
        return requestedTimeForVacation;
    }

    public void setRequestedTimeForVacation(TimeInterval requestedTimeForVacation) {
        this.requestedTimeForVacation = requestedTimeForVacation;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacationTimeRequest that = (VacationTimeRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(requestedTimeForVacation, that.requestedTimeForVacation) &&
                status == that.status &&
                Objects.equals(rejectedReason, that.rejectedReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestedTimeForVacation, status, rejectedReason);
    }
}