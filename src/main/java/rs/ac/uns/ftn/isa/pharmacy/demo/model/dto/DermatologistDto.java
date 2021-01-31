package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DermatologistDto implements Serializable {

    private String name;
    private String surname;
    private Double rating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DermatologistDto that = (DermatologistDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, rating);
    }

    @Override
    public String toString() {
        return "DermatologistDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                '}';
    }
}
