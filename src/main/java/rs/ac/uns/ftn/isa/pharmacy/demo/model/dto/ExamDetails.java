package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Calendar;

public class ExamDetails {
    private Long id;
    private Calendar examStart;
    private Calendar examEnd;
    private double price;
    private String dermatologistName;
    private String dermatologistSurname;
    private String pharmacyName;

    public ExamDetails() {
    }

    public ExamDetails(Long id, double price, Calendar examStart, Calendar examEnd, String dermatologistName, String dermatologistSurname, String pharmacyName) {
        this.id = id;
        this.price = price;
        this.examStart = examStart;
        this.examEnd = examEnd;
        this.dermatologistName = dermatologistName;
        this.dermatologistSurname = dermatologistSurname;
        this.pharmacyName = pharmacyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getExamStart() {
        return examStart;
    }

    public void setExamStart(Calendar examStart) {
        this.examStart = examStart;
    }

    public Calendar getExamEnd() {
        return examEnd;
    }

    public void setExamEnd(Calendar examEnd) {
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

    @Override
    public String toString() {
        return "ExamDetails{" +
                "examStart=" + examStart +
                ", examEnd=" + examEnd +
                ", price=" + price +
                ", dermatologistName='" + dermatologistName + '\'' +
                ", dermatologistSurname='" + dermatologistSurname + '\'' +
                ", pharmacyName='" + pharmacyName + '\'' +
                '}';
    }
}
