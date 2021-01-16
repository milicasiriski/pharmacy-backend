package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Date;

public class MedicineReservationDto {
    private String medicineId;
    private String pharmacyId;
    private Date expirationDate;

    public MedicineReservationDto() {
    }

    public MedicineReservationDto(String medicineId, String pharmacyId, Date expirationDate) {
        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.expirationDate = expirationDate;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
