package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetPharmacistExamResponse implements Serializable {
    private Long examId;
    private Date date;
    private String start;
    private String end;
    private String pharmacistName;
    private String pharmacistSurname;
    private double pharmacistRating;
    private double price;

    public GetPharmacistExamResponse() {
    }

    public GetPharmacistExamResponse(Long examId, Date date, String start, String end, String pharmacistName, String pharmacistSurname, double pharmacistRating, double price) {
        this.examId = examId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.pharmacistName = pharmacistName;
        this.pharmacistSurname = pharmacistSurname;
        this.pharmacistRating = pharmacistRating;
        this.price = price;
    }

    public GetPharmacistExamResponse(Exam exam, Pharmacist pharmacist) {
        this.examId = exam.getId();
        this.date = exam.getTimeInterval().getStart().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        this.start = dateFormat.format(exam.getTimeInterval().getStart().getTime());
        this.end = dateFormat.format(exam.getTimeInterval().getEnd().getTime());
        this.pharmacistName = pharmacist.getName();
        this.pharmacistSurname = pharmacist.getSurname();
        this.pharmacistRating = pharmacist.getRating();
        this.price = exam.getPrice();
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }

    public String getPharmacistSurname() {
        return pharmacistSurname;
    }

    public void setPharmacistSurname(String pharmacistSurname) {
        this.pharmacistSurname = pharmacistSurname;
    }

    public double getPharmacistRating() {
        return pharmacistRating;
    }

    public void setPharmacistRating(double pharmacistRating) {
        this.pharmacistRating = pharmacistRating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
