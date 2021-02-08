package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

import java.util.List;

public class MedicineMonthStatisticDto {

    List<Integer> medicinePerMonth;

    public MedicineMonthStatisticDto() {

    }

    public MedicineMonthStatisticDto(List<Integer> medicinePerMonth) {
        this.medicinePerMonth = medicinePerMonth;
    }

    public List<Integer> getMedicinePerMonth() {
        return medicinePerMonth;
    }

    public void setMedicinePerMonth(List<Integer> medicinePerMonth) {
        this.medicinePerMonth = medicinePerMonth;
    }
}
