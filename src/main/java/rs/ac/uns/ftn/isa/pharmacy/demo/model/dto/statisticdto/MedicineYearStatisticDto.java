package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto;

public class MedicineYearStatisticDto {

    Integer year;
    Integer count;

    MedicineYearStatisticDto() {

    }

    public MedicineYearStatisticDto(Integer year, Integer count) {
        this.year = year;
        this.count = count;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
