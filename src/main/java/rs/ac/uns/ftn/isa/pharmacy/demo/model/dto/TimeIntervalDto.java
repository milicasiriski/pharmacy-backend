package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Date;

public class TimeIntervalDto {

    private Date start;
    private Date end;

    public TimeIntervalDto() {

    }

    public TimeIntervalDto(Date start, Date end) {
        this.start = start;
        this.end = end;
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
