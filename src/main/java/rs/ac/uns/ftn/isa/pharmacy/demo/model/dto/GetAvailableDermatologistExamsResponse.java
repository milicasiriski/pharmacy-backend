package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetAvailableDermatologistExamsResponse implements Serializable {

    private Long examId;
    private Date date;
    private String start;
    private String end;
    private String dermatologistName;
    private String dermatologistSurname;
    private double dermatologistRating;

    public GetAvailableDermatologistExamsResponse() {

    }

    public GetAvailableDermatologistExamsResponse(Exam exam, Dermatologist dermatologist) {
        this.examId = exam.getId();
        this.date = exam.getTimeInterval().getStart().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        this.start = dateFormat.format(exam.getTimeInterval().getStart().getTime());
        this.end = dateFormat.format(exam.getTimeInterval().getEnd().getTime());
        this.dermatologistName = dermatologist.getName();
        this.dermatologistSurname = dermatologist.getSurname();
        this.dermatologistRating = dermatologist.getRating();
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

    public double getDermatologistRating() {
        return dermatologistRating;
    }

    public void setDermatologistRating(double dermatologistRating) {
        this.dermatologistRating = dermatologistRating;
    }
}
