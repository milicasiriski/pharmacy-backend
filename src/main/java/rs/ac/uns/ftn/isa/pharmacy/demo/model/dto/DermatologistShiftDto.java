package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;

import java.util.List;
import java.util.Objects;

public class DermatologistShiftDto {

    Dermatologist dermatologist;
    List<String> hourIntervals;
    String durationInMinutes;
    String price;

    public DermatologistShiftDto() {

    }

    public DermatologistShiftDto(Dermatologist dermatologist, List<String> hourIntervals, String durationInMinutes, String price) {
        this.dermatologist = dermatologist;
        this.hourIntervals = hourIntervals;
        this.durationInMinutes = durationInMinutes;
        this.price = price;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public List<String> getHourIntervals() {
        return hourIntervals;
    }

    public void setHourIntervals(List<String> hourIntervals) {
        this.hourIntervals = hourIntervals;
    }

    public String getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(String durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DermatologistShiftDto that = (DermatologistShiftDto) o;
        return Objects.equals(dermatologist, that.dermatologist) &&
                Objects.equals(hourIntervals, that.hourIntervals) &&
                Objects.equals(durationInMinutes, that.durationInMinutes) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dermatologist, hourIntervals, durationInMinutes, price);
    }

    @Override
    public String toString() {
        return "DermatologistShiftDto{" +
                "dermatologist=" + dermatologist +
                ", hourIntervals=" + hourIntervals +
                ", durationInMinutes='" + durationInMinutes + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
