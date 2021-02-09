package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DermatologistDto implements Serializable {

    private String name;
    private String surname;
    private Double rating;
    private String email;
    private long id;
    private List<PharmacyNameAndAddressDto> pharmacies;

    public DermatologistDto(String name, String surname, Double rating, long id, List<PharmacyNameAndAddressDto> pharmacies) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.id = id;
        this.pharmacies = pharmacies;
    }

    public DermatologistDto(String name, String surname, Double rating, long id) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.id = id;
    }

    public DermatologistDto(String name, String surname, String email, long id) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.id = id;
    }

    public DermatologistDto(Dermatologist dermatologist) {
        this.name = dermatologist.getName();
        this.surname = dermatologist.getSurname();
        this.rating = dermatologist.getRating();
        this.id = dermatologist.getId();
        this.email = dermatologist.getEmail();
    }

    public List<PharmacyNameAndAddressDto> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyNameAndAddressDto> pharmacies) {
        this.pharmacies = pharmacies;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
