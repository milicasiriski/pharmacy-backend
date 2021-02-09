package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.RatingPharmacy;

import java.io.Serializable;

public class PharmacyRatingDto implements Serializable {
    private long id;
    private String name;
    private AddressDto address;
    private double rating;
    private Integer myRating;

    public PharmacyRatingDto() {
    }

    public PharmacyRatingDto(long id, String name, AddressDto address, double rating, Integer myRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.myRating = myRating;
    }

    public PharmacyRatingDto(Pharmacy pharmacy, RatingPharmacy ratingPharmacy) {
        this.id = pharmacy.getId();
        this.name = pharmacy.getName();
        this.address = new AddressDto(pharmacy.getAddress());
        this.rating = pharmacy.getRating();
        if (ratingPharmacy != null) {
            this.myRating = ratingPharmacy.getRating();
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
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
