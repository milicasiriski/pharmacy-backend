package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;

import java.io.Serializable;

public class GetPharmacistsForPharmacistExamResponse implements Serializable {
    private String name;
    private String surname;
    private double rating;

    public GetPharmacistsForPharmacistExamResponse() {
    }

    public GetPharmacistsForPharmacistExamResponse(String name, String surname, double rating) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
    }

    public GetPharmacistsForPharmacistExamResponse(Pharmacist pharmacist) {
        this.name = pharmacist.getName();
        this.surname = pharmacist.getSurname();
        this.rating = pharmacist.getRating();
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
}
