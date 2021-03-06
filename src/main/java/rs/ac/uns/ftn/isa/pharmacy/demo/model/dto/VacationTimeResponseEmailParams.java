package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class VacationTimeResponseEmailParams {
    private String status;
    private String reason;

    public VacationTimeResponseEmailParams() {
    }

    public VacationTimeResponseEmailParams(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
