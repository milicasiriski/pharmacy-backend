package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class PharmaciesMedicinePriceDto implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String about;
    private Double price;

    public PharmaciesMedicinePriceDto() {
    }

    public PharmaciesMedicinePriceDto(Long id, String name, String address, String about, Double price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.about = about;
        this.price = price;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
