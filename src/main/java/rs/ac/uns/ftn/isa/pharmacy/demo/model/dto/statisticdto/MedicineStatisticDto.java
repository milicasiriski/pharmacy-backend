package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

import java.util.List;

public class MedicineStatisticDto {

    MedicineMonthStatisticDto medicineMonthStatistic;
    List<MedicineYearStatisticDto> medicineYearStatistic;
    MedicineQuarterStatisticDto medicineQuarterStatistic;

    public MedicineStatisticDto() {

    }

    public MedicineStatisticDto(MedicineMonthStatisticDto medicineMonthStatistic, List<MedicineYearStatisticDto> medicineYearStatistic, MedicineQuarterStatisticDto medicineQuarterStatistic) {
        this.medicineMonthStatistic = medicineMonthStatistic;
        this.medicineYearStatistic = medicineYearStatistic;
        this.medicineQuarterStatistic = medicineQuarterStatistic;
    }

    public MedicineMonthStatisticDto getMedicineMonthStatistic() {
        return medicineMonthStatistic;
    }

    public void setMedicineMonthStatistic(MedicineMonthStatisticDto medicineMonthStatistic) {
        this.medicineMonthStatistic = medicineMonthStatistic;
    }

    public List<MedicineYearStatisticDto> getMedicineYearStatistic() {
        return medicineYearStatistic;
    }

    public void setMedicineYearStatistic(List<MedicineYearStatisticDto> medicineYearStatistic) {
        this.medicineYearStatistic = medicineYearStatistic;
    }

    public MedicineQuarterStatisticDto getMedicineQuarterStatistic() {
        return medicineQuarterStatistic;
    }

    public void setMedicineQuarterStatistic(MedicineQuarterStatisticDto medicineQuarterStatistic) {
        this.medicineQuarterStatistic = medicineQuarterStatistic;
    }
}
