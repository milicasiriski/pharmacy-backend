package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Date;

public class PharmacyAdminExamDto {

    private Long dermatologistId;
    private Date examStart;
    private int duration;
    private double price;

    public PharmacyAdminExamDto() {

    }

    public PharmacyAdminExamDto(long dermatologistId, Date examStart, int duration, double price) {
        this.dermatologistId = dermatologistId;
        this.examStart = examStart;
        this.duration = duration;
        this.price = price;
    }

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public Date getExamStart() {
        return examStart;
    }

    public void setExamStart(Date examStart) {
        this.examStart = examStart;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}