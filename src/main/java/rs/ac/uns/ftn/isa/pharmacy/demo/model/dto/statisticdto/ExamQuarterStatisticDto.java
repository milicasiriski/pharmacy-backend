package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

import java.util.List;

public class ExamQuarterStatisticDto {

    List<Integer> examsPerQuarter;

    public ExamQuarterStatisticDto() {

    }

    public ExamQuarterStatisticDto(List<Integer> examsPerQuarter) {
        this.examsPerQuarter = examsPerQuarter;
    }

    public List<Integer> getExamsPerQuarter() {
        return examsPerQuarter;
    }

    public void setExamsPerQuarter(List<Integer> examsPerQuarter) {
        this.examsPerQuarter = examsPerQuarter;
    }
}
