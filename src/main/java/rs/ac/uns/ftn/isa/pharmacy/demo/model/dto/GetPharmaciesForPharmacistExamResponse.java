package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

import java.io.Serializable;

public class GetPharmaciesForPharmacistExamResponse implements Serializable {
    private Long id;
    private String name;
    private String address;
    private Double rating;
    private Double price;

    public GetPharmaciesForPharmacistExamResponse() {
    }

    public GetPharmaciesForPharmacistExamResponse(Long id, String name, String address, Double rating, Double price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.price = price;
    }

    public GetPharmaciesForPharmacistExamResponse(Pharmacy pharmacy) {
        this.id = pharmacy.getId();
        this.name = pharmacy.getName();
        this.address = pharmacy.getAddress().getCity() + ", " + pharmacy.getAddress().getStreet();
        this.rating = pharmacy.getRating();
        this.price = 0.0; // TODO: add pharmacist exam price to pharmacy model
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
