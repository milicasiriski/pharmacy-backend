package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class SchedulePharmacistExamParams implements Serializable {
    private Date dateTime;
    private long pharmacistId;

    public SchedulePharmacistExamParams() {
    }

    public SchedulePharmacistExamParams(Date dateTime, long pharmacistId) {
        this.dateTime = dateTime;
        this.pharmacistId = pharmacistId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }
}
