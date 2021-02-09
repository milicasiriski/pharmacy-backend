package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

import java.io.Serializable;
import java.util.Objects;

public class PharmacyDto implements Serializable {

    private Long id;
    private String name;
    private String about;
    private AddressDto address;
    private Double rating;
    private Double pharmacistExamPrice;

    public PharmacyDto() {
    }

    public PharmacyDto(String name, AddressDto address, String about, Long id, Double rating, double pharmacistExamPrice) {
        this.name = name;
        this.address = address;
        this.about = about;
        this.id = id;
        this.rating = rating;
        this.pharmacistExamPrice = pharmacistExamPrice;
    }

    public PharmacyDto(Pharmacy pharmacy) {
        this.id = pharmacy.getId();
        this.name = pharmacy.getName();
        this.about = pharmacy.getAbout();
        this.address = new AddressDto(pharmacy.getAddress());
        this.rating = pharmacy.getRating();
        this.pharmacistExamPrice = pharmacy.getPharmacistExamPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPharmacistExamPrice() {
        return pharmacistExamPrice;
    }

    public void setPharmacistExamPrice(Double pharmacistExamPrice) {
        this.pharmacistExamPrice = pharmacistExamPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyDto that = (PharmacyDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
