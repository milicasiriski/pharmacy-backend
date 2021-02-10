package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.List;

public class DermatologistShiftDto implements Serializable {

    DermatologistDto dermatologist;
    List<String> hourIntervals;
    String durationInMinutes;
    String price;

    public DermatologistShiftDto() {

    }

    public DermatologistShiftDto(DermatologistDto dermatologist, List<String> hourIntervals) {
        this.dermatologist = dermatologist;
        this.hourIntervals = hourIntervals;
    }

    public DermatologistDto getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(DermatologistDto dermatologist) {
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
}
