package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacation_time_request")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class VacationTimeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "time_start")),
            @AttributeOverride(name = "end", column = @Column(name = "time_end"))
    })
    protected TimeInterval requestedTimeForVacation;

    @Column(name = "approved")
    protected boolean isApproved;

    @Column(name = "rejected_reason")
    protected String rejectedReason;

    protected VacationTimeRequest() {

    }

    protected VacationTimeRequest(TimeInterval requestedTimeForVacation, boolean isApproved, String rejectedReason) {
        this.requestedTimeForVacation = requestedTimeForVacation;
        this.isApproved = isApproved;
        this.rejectedReason = rejectedReason;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
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
        return isApproved == that.isApproved &&
                requestedTimeForVacation.equals(that.requestedTimeForVacation) &&
                rejectedReason.equals(that.rejectedReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedTimeForVacation, isApproved, rejectedReason);
    }
}
