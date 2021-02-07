package rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping;

import rs.ac.uns.ftn.isa.pharmacy.demo.util.Constants;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ExamDetails implements Serializable {
    private Long id;
    private Calendar examStart;
    private Calendar examEnd;
    private double price;
    private String examinerName;
    private String examinerSurname;
    private String pharmacyName;
    private boolean cancellable;

    public ExamDetails() {
    }

    public ExamDetails(Long id, double price, Calendar examStart, Calendar examEnd, String examinerName, String examinerSurname, String pharmacyName) {
        this.id = id;
        this.price = price;
        this.examStart = examStart;
        this.examEnd = examEnd;
        this.examinerName = examinerName;
        this.examinerSurname = examinerSurname;
        this.pharmacyName = pharmacyName;
        this.cancellable = isExamCancellable();
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

    public String getExaminerName() {
        return examinerName;
    }

    public void setExaminerName(String examinerName) {
        this.examinerName = examinerName;
    }

    public String getExaminerSurname() {
        return examinerSurname;
    }

    public void setExaminerSurname(String examinerSurname) {
        this.examinerSurname = examinerSurname;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    private boolean isExamCancellable() {
        Calendar now = Calendar.getInstance();
        long differenceInMilliseconds = examStart.getTime().getTime() - now.getTime().getTime();
        long differenceInHours = TimeUnit.HOURS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);

        return differenceInHours >= Constants.DERMATOLOGIST_EXAM_CANCELLATION_HOURS;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    @Override
    public String toString() {
        return "ExamDetails{" +
                "examStart=" + examStart +
                ", examEnd=" + examEnd +
                ", price=" + price +
                ", dermatologistName='" + examinerName + '\'' +
                ", dermatologistSurname='" + examinerSurname + '\'' +
                ", pharmacyName='" + pharmacyName + '\'' +
                '}';
    }
}
