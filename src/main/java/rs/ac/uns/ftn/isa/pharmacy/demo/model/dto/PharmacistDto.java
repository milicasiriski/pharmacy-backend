package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PharmacistDto implements Serializable {

    private String name;
    private String surname;
    private Double rating;
    private Long id;
    private List<PharmacyNameAndAddressDto> pharmacies;

    public PharmacistDto() {

    }

    public PharmacistDto(String name, String surname, Double rating, List<PharmacyNameAndAddressDto> pharmacies) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.pharmacies = pharmacies;
    }

    public PharmacistDto(String name, String surname, Double rating, Long id) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.id = id;
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

    public List<PharmacyNameAndAddressDto> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyNameAndAddressDto> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacistDto that = (PharmacistDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(pharmacies, that.pharmacies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, rating, pharmacies);
    }

    @Override
    public String toString() {
        return "PharmacistDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                ", pharmacies=" + pharmacies +
                '}';
    }
}
