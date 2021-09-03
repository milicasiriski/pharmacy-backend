package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class VacationRequestPharmacistDTO implements Serializable {
    private Date startDate;
    private Date endDate;

    public VacationRequestPharmacistDTO() {
    }

    public VacationRequestPharmacistDTO(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
