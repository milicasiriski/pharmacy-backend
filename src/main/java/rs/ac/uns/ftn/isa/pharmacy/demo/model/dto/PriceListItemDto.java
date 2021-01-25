package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class PriceListItemDto implements Serializable {

    Double currentPrice;
    String startDate;
    String endDate;

    public PriceListItemDto() {

    }

    public PriceListItemDto(Double currentPrice, String startDate, String endDate) {
        this.currentPrice = currentPrice;
        this.startDate = startDate;
        this.endDate = endDate;
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
        PriceListItemDto that = (PriceListItemDto) o;
        return Objects.equals(currentPrice, that.currentPrice) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPrice, startDate, endDate);
    }

    @Override
    public String toString() {
        return "PriceListItemDto{" +
                "currentPrice=" + currentPrice +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
