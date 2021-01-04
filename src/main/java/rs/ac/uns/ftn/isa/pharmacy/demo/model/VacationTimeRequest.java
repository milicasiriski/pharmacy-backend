package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import java.util.Objects;

public abstract class VacationTimeRequest {

    protected TimeInterval requestedTimeForVacation;
    protected boolean isApproved;
    protected String rejectedReason;

    protected VacationTimeRequest() {

    }

    protected VacationTimeRequest(TimeInterval requestedTimeForVacation, boolean isApproved, String rejectedReason) {
        this.requestedTimeForVacation = requestedTimeForVacation;
        this.isApproved = isApproved;
        this.rejectedReason = rejectedReason;
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
