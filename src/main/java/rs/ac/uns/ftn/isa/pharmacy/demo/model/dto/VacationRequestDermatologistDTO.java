package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class VacationRequestDermatologistDTO implements Serializable {
    private Long pharmacyID;
    private Date startDate;
    private Date endDate;

    public VacationRequestDermatologistDTO() {
    }

    public VacationRequestDermatologistDTO(Long pharmacyID, Date startDate, Date endDate) {
        this.pharmacyID = pharmacyID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
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
