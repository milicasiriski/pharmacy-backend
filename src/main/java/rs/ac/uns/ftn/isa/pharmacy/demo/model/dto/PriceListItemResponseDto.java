package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class PriceListItemResponseDto implements Serializable {

    MedicinesBasicInfoDto medicineInfo;
    Double currentPrice;
    String startDate;
    String endDate;

    public PriceListItemResponseDto() {

    }

    public PriceListItemResponseDto(MedicinesBasicInfoDto medicineInfo, Double currentPrice, String startDate, String endDate) {
        this.medicineInfo = medicineInfo;
        this.currentPrice = currentPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public MedicinesBasicInfoDto getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(MedicinesBasicInfoDto medicineInfo) {
        this.medicineInfo = medicineInfo;
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
}
