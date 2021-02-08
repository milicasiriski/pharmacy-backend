package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

import java.util.List;

public class ExamMonthStatisticDto {

    List<Integer> examsPerMonth;

    public ExamMonthStatisticDto() {

    }

    public ExamMonthStatisticDto(List<Integer> examsPerMonth) {
        this.examsPerMonth = examsPerMonth;
    }

    public List<Integer> getExamsPerMonth() {
        return examsPerMonth;
    }

    public void setExamsPerMonth(List<Integer> examsPerMonth) {
        this.examsPerMonth = examsPerMonth;
    }
}
