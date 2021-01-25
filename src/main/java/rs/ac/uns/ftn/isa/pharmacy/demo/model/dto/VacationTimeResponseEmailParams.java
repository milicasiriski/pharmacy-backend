package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class VacationTimeResponseEmailParams {
    private boolean approved;
    private String reason;

    public VacationTimeResponseEmailParams() {
    }

    public VacationTimeResponseEmailParams(boolean approved, String reason) {
        this.approved = approved;
        this.reason = reason;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
