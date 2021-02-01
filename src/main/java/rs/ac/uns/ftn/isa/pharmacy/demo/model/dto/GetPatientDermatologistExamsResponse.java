package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;

import java.io.Serializable;
import java.util.Date;

public class GetPatientDermatologistExamsResponse implements Serializable {
    private Long id;
    private Date examStart;
    private Date examEnd;
    private double price;
    private String dermatologistName;
    private String dermatologistSurname;
    private String pharmacyName;
    private boolean cancellable;

    public GetPatientDermatologistExamsResponse() {
    }

    public GetPatientDermatologistExamsResponse(Long id, Date examStart, Date examEnd, double price, String dermatologistName, String dermatologistSurname, String pharmacyName, boolean cancellable) {
        this.id = id;
        this.examStart = examStart;
        this.examEnd = examEnd;
        this.price = price;
        this.dermatologistName = dermatologistName;
        this.dermatologistSurname = dermatologistSurname;
        this.pharmacyName = pharmacyName;
        this.cancellable = cancellable;
    }

    public GetPatientDermatologistExamsResponse(ExamDetails examDetails) {
        this.id = examDetails.getId();
        this.examStart = examDetails.getExamStart().getTime();
        this.examEnd = examDetails.getExamEnd().getTime();
        this.price = examDetails.getPrice();
        this.dermatologistName = examDetails.getDermatologistName();
        this.dermatologistSurname = examDetails.getDermatologistSurname();
        this.pharmacyName = examDetails.getPharmacyName();
        this.cancellable = examDetails.isExamCancellable();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExamStart() {
        return examStart;
    }

    public void setExamStart(Date examStart) {
        this.examStart = examStart;
    }

    public Date getExamEnd() {
        return examEnd;
    }

    public void setExamEnd(Date examEnd) {
        this.examEnd = examEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDermatologistName() {
        return dermatologistName;
    }

    public void setDermatologistName(String dermatologistName) {
        this.dermatologistName = dermatologistName;
    }

    public String getDermatologistSurname() {
        return dermatologistSurname;
    }

    public void setDermatologistSurname(String dermatologistSurname) {
        this.dermatologistSurname = dermatologistSurname;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }
}
