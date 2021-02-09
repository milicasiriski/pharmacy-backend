package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class MedicineSearchDto {

    private MedicinesBasicInfoDto medicine;
    private String searchedDate;

    public MedicineSearchDto() {

    }

    public MedicineSearchDto(MedicinesBasicInfoDto medicine, String searchedDate) {
        this.medicine = medicine;
        this.searchedDate = searchedDate;
    }

    public MedicinesBasicInfoDto getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicinesBasicInfoDto medicine) {
        this.medicine = medicine;
    }

    public String getSearchedDate() {
        return searchedDate;
    }

    public void setSearchedDate(String searchedDate) {
        this.searchedDate = searchedDate;
    }
}
