package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import java.util.Objects;

public abstract class Exam {
    protected int duration;
    protected double price;

    protected Exam() {
    }

    protected Exam(int duration, double price) {
        this.duration = duration;
        this.price = price;
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
        Exam exam = (Exam) o;
        return duration == exam.duration &&
                Double.compare(exam.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, price);
    }
}
