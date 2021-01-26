package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;

import java.io.Serializable;
import java.util.Objects;

public class PriceListItemResponseDto implements Serializable {

    Medicine medicine;
    Double currentPrice;
    String startDate;
    String endDate;

    public PriceListItemResponseDto() {

    }

    public PriceListItemResponseDto(Medicine medicine, Double currentPrice, String startDate, String endDate) {
        this.medicine = medicine;
        this.currentPrice = currentPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceListItemResponseDto that = (PriceListItemResponseDto) o;
        return Objects.equals(medicine, that.medicine) &&
                Objects.equals(currentPrice, that.currentPrice) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicine, currentPrice, startDate, endDate);
    }
}
