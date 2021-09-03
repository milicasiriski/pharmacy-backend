package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class DermatologistExamDTO implements Serializable {

    private long dermatologistId;
    private long pharmacyID;
    private String patientID;
    private Date examStart;
    private int duration;
    private double price;

    public DermatologistExamDTO() {
    }

    public DermatologistExamDTO(long dermatologistId, long pharmacyID, String patientID,  Date examStart, int duration, double price) {
        this.dermatologistId = dermatologistId;
        this.pharmacyID = pharmacyID;
        this.patientID = patientID;
        this.examStart = examStart;
        this.duration = duration;
        this.price = price;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(long pharmacyID) {
        this.pharmacyID = pharmacyID;
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
