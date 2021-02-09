package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class MedicineAmountForOfferDto {
    MedicineDto medicine;
    Integer amount;

    public MedicineAmountForOfferDto(MedicineDto medicine, Integer amount) {
        this.medicine = medicine;
        this.amount = amount;
    }

    public MedicineAmountForOfferDto() {
    }

    public MedicineDto getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineDto medicine) {
        this.medicine = medicine;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
