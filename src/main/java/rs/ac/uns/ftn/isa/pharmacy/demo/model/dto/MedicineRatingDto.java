package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.RatingMedicine;

import java.io.Serializable;

public class MedicineRatingDto implements Serializable {
    private long id;
    private String name;
    private String form;
    private String manufacturer;
    private double rating;
    private Integer myRating;

    public MedicineRatingDto() {
    }

    public MedicineRatingDto(long id, String name, String form, String manufacturer, double rating, Integer myRating) {
        this.id = id;
        this.name = name;
        this.form = form;
        this.manufacturer = manufacturer;
        this.rating = rating;
        this.myRating = myRating;
    }

    public MedicineRatingDto(Medicine medicine, RatingMedicine ratingMedicine) {
        this.id = medicine.getId();
        this.name = medicine.getName();
        this.form = medicine.getForm().label;
        this.manufacturer = medicine.getManufacturer();
        this.rating = medicine.getRatings();
        if (ratingMedicine != null) {
            this.myRating = ratingMedicine.getRating();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getMyRating() {
        return myRating;
    }

    public void setMyRating(Integer myRating) {
        this.myRating = myRating;
    }
}
