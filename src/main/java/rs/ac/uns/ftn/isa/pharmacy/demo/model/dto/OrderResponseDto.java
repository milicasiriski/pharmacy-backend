package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.List;
import java.util.Objects;

public class OrderResponseDto {

    private String deadlineString;
    private List<MedicineAmountDto> medicineAmount;

    public OrderResponseDto() {

    }

    public OrderResponseDto(String deadlineString, List<MedicineAmountDto> medicineAmount) {
        this.deadlineString = deadlineString;
        this.medicineAmount = medicineAmount;
    }

    public String getDeadlineString() {
        return deadlineString;
    }

    public void setDeadlineString(String deadlineString) {
        this.deadlineString = deadlineString;
    }

    public List<MedicineAmountDto> getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(List<MedicineAmountDto> medicineAmount) {
        this.medicineAmount = medicineAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponseDto that = (OrderResponseDto) o;
        return Objects.equals(deadlineString, that.deadlineString) &&
                Objects.equals(medicineAmount, that.medicineAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deadlineString, medicineAmount);
    }

    @Override
    public String toString() {
        return "OrderResponseDto{" +
                "deadlineString='" + deadlineString + '\'' +
                ", medicineAmount=" + medicineAmount +
                '}';
    }
}
