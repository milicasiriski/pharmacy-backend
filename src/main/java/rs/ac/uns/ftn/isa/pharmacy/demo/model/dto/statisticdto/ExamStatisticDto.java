package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

import java.util.List;

public class ExamStatisticDto {

    ExamMonthStatisticDto examMonthStatistic;
    List<ExamYearStatisticDto> examYearStatistic;
    ExamQuarterStatisticDto examQuarterStatistic;

    public ExamStatisticDto() {

    }

    public ExamStatisticDto(ExamMonthStatisticDto examMonthStatistic, List<ExamYearStatisticDto> examYearStatistic,
                            ExamQuarterStatisticDto examQuarterStatistic) {
        this.examMonthStatistic = examMonthStatistic;
        this.examYearStatistic = examYearStatistic;
        this.examQuarterStatistic = examQuarterStatistic;
    }

    public ExamQuarterStatisticDto getExamQuarterStatistic() {
        return examQuarterStatistic;
    }

    public void setExamQuarterStatistic(ExamQuarterStatisticDto examQuarterStatistic) {
        this.examQuarterStatistic = examQuarterStatistic;
    }

    public ExamMonthStatisticDto getExamMonthStatistic() {
        return examMonthStatistic;
    }

    public void setExamMonthStatistic(ExamMonthStatisticDto examMonthStatistic) {
        this.examMonthStatistic = examMonthStatistic;
    }

    public List<ExamYearStatisticDto> getExamYearStatistic() {
        return examYearStatistic;
    }

    public void setExamYearStatistic(List<ExamYearStatisticDto> examYearStatistic) {
        this.examYearStatistic = examYearStatistic;
    }
}
