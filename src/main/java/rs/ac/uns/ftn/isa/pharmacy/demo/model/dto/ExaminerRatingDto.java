package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Rating;

import java.io.Serializable;

public class ExaminerRatingDto implements Serializable {
    private long id;
    private String name;
    private String surname;
    private double rating;
    private Integer myRating;

    public ExaminerRatingDto() {
    }

    public ExaminerRatingDto(long id, String name, String surname, double rating, Integer myRating) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.myRating = myRating;
    }

    public ExaminerRatingDto(Dermatologist dermatologist, Rating rating) {
        this.id = dermatologist.getId();
        this.name = dermatologist.getName();
        this.surname = dermatologist.getSurname();
        this.rating = dermatologist.getRating();
        if (rating != null) {
            this.myRating = rating.getRating();
        }
    }

    public ExaminerRatingDto(Pharmacist pharmacist, Rating rating) {
        this.id = pharmacist.getId();
        this.name = pharmacist.getName();
        this.surname = pharmacist.getSurname();
        this.rating = pharmacist.getRating();
        if (rating != null) {
            this.myRating = rating.getRating();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
