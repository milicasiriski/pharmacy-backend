package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class MedicineReservationDto implements Serializable {
    private Long medicineId;
    private Long pharmacyId;
    private Date expirationDate;

    public MedicineReservationDto() {
    }

    public MedicineReservationDto(Long medicineId, Long pharmacyId, Date expirationDate) {
        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.expirationDate = expirationDate;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
