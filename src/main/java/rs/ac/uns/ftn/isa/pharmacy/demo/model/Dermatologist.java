package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
@DiscriminatorValue("DERMATOLOGIST")
public class Dermatologist extends User {

    private transient final String administrationRole = "ROLE_DERMATOLOGIST";

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "dermatologist_employment_mapping",
            joinColumns = {@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dermatologist_employment_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "pharmacy_id")
    private Map<Pharmacy, Employment> pharmacies;

    @Column(name = "rating")
    private double rating;

    public Dermatologist() {
        super();
    }

    public Dermatologist(String email, String password, String name, String surname, Map<Pharmacy, Employment> pharmacies, double rating) {
        super(email, password, name, surname, new Address());
        this.name = name;
        this.surname = surname;
        this.pharmacies = pharmacies;
        this.rating = rating;
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    public Map<Pharmacy, Employment> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Map<Pharmacy, Employment> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dermatologist that = (Dermatologist) o;
        return Objects.equals(administrationRole, that.administrationRole) &&
                Objects.equals(pharmacies, that.pharmacies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole, pharmacies);
    }

    @Override
    public String toString() {
        return "Dermatologist{" +
                "administrationRole='" + administrationRole + '\'' +
                ", pharmacies=" + pharmacies +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
