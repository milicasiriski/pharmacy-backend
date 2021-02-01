package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.List;

public class OrderResponseDto {

    private String deadlineString;
    private List<MedicineAmountDto> medicineAmount;
    private Long adminId;

    public OrderResponseDto() {

    }

    public OrderResponseDto(String deadlineString, List<MedicineAmountDto> medicineAmount, Long adminId) {
        this.deadlineString = deadlineString;
        this.medicineAmount = medicineAmount;
        this.adminId = adminId;
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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
