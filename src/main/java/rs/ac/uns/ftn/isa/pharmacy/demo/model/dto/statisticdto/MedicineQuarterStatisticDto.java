package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

import java.util.List;

public class MedicineQuarterStatisticDto {

    List<Integer> medicinesPerQuarter;

    MedicineQuarterStatisticDto() {

    }

    public MedicineQuarterStatisticDto(List<Integer> medicinesPerQuarter) {
        this.medicinesPerQuarter = medicinesPerQuarter;
    }

    public List<Integer> getMedicinesPerQuarter() {
        return medicinesPerQuarter;
    }

    public void setMedicinesPerQuarter(List<Integer> medicinesPerQuarter) {
        this.medicinesPerQuarter = medicinesPerQuarter;
    }
}
