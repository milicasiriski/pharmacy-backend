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
    private String dermatologistName;
    private String dermatologistSurname;
    private String pharmacyName;
    private boolean cancellable;

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
                ", dermatologistName='" + dermatologistName + '\'' +
                ", dermatologistSurname='" + dermatologistSurname + '\'' +
                ", pharmacyName='" + pharmacyName + '\'' +
                '}';
    }
}
