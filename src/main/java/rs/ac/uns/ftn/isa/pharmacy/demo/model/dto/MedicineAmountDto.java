package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;


import java.io.Serializable;

public class MedicineAmountDto implements Serializable {
    private String medicineName;
    private int medicineAmount;
    private String uuid;

    public MedicineAmountDto(String medicineName, int medicineAmount) {
        this.medicineName = medicineName;
        this.medicineAmount = medicineAmount;
    }

    public MedicineAmountDto() {

    }

    public MedicineAmountDto(String medicineName, String uuid, int medicineAmount) {
        this.medicineName = medicineName;
        this.medicineAmount = medicineAmount;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(int medicineAmount) {
        this.medicineAmount = medicineAmount;
    }

}
