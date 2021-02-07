package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Date;

public class TimeIntervalDto {

    private Date start;
    private Date end;
    private Boolean shiftDefined;

    public TimeIntervalDto() {

    }

    public TimeIntervalDto(Date start, Date end, Boolean shiftDefined) {
        this.start = start;
        this.end = end;
        this.shiftDefined = shiftDefined;
    }

    public Boolean getShiftDefined() {
        return shiftDefined;
    }

    public void setShiftDefined(Boolean shiftDefined) {
        this.shiftDefined = shiftDefined;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
