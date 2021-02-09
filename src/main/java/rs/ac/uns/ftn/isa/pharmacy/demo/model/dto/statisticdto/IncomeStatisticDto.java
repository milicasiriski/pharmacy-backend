package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

import java.util.List;

public class IncomeStatisticDto {

    List<Double> incomePerMonth;

    public IncomeStatisticDto() {

    }

    public IncomeStatisticDto(List<Double> incomePerMonth) {
        this.incomePerMonth = incomePerMonth;
    }

    public List<Double> getIncomePerMonth() {
        return incomePerMonth;
    }

    public void setIncomePerMonth(List<Double> incomePerMonth) {
        this.incomePerMonth = incomePerMonth;
    }
}
