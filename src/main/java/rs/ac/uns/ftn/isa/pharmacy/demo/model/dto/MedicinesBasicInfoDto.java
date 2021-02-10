package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class MedicinesBasicInfoDto implements Serializable {

    String name;
    String form;
    Double rating;
    Long id;
    Double currentPrice;

    public MedicinesBasicInfoDto() {
    }

    public MedicinesBasicInfoDto(String name, String form, Long id, Double rating) {
        this.name = name;
        this.form = form;
        this.id = id;
        this.rating = rating;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
