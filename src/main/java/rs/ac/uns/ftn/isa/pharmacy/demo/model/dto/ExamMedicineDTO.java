package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class ExamMedicineDTO implements Serializable {
    private Long medicineId;
    private Long pharmacyId;
    private Date expirationDate;
    private String patientId;

    public ExamMedicineDTO() {
    }

    public ExamMedicineDTO(Long medicineId, Long pharmacyId, Date expirationDate, String patientId) {
        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.expirationDate = expirationDate;
        this.patientId = patientId;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
