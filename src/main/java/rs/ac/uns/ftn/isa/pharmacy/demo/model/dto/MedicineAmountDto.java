package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Objects;

public class MedicineAmountDto {
    private String medicineName;
    private int medicineAmount;

    public MedicineAmountDto(String medicineName, int medicineAmount) {
        this.medicineName = medicineName;
        this.medicineAmount = medicineAmount;
    }

    public MedicineAmountDto() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineAmountDto that = (MedicineAmountDto) o;
        return medicineAmount == that.medicineAmount &&
                medicineName.equals(that.medicineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineName, medicineAmount);
    }
}
