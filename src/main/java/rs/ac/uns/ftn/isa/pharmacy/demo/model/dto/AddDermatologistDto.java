package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.List;

public class AddDermatologistDto {

    private Long dermatologistId;
    private List<TimeIntervalDto> shifts;

    public AddDermatologistDto() {

    }

    public AddDermatologistDto(Long dermatologistId, List<TimeIntervalDto> shifts) {
        this.dermatologistId = dermatologistId;
        this.shifts = shifts;
    }

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public List<TimeIntervalDto> getShifts() {
        return shifts;
    }

    public void setShifts(List<TimeIntervalDto> shifts) {
        this.shifts = shifts;
    }
}
