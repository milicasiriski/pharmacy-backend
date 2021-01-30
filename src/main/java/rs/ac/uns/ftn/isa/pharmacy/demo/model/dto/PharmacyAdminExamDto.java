package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Date;
import java.util.Objects;

public class PharmacyAdminExamDto {

    private String dermatologistId;
    private Date examStart;
    private int duration;
    private double price;

    public PharmacyAdminExamDto() {

    }

    public PharmacyAdminExamDto(String dermatologistId, Date examStart, int duration, double price) {
        this.dermatologistId = dermatologistId;
        this.examStart = examStart;
        this.duration = duration;
        this.price = price;
    }

    public String getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(String dermatologistId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyAdminExamDto that = (PharmacyAdminExamDto) o;
        return duration == that.duration &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(dermatologistId, that.dermatologistId) &&
                Objects.equals(examStart, that.examStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dermatologistId, examStart, duration, price);
    }
}