package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Entity
@DiscriminatorValue("DERMATOLOGIST")
public class Dermatologist extends User implements Serializable {

    private transient final String administrationRole = "ROLE_DERMATOLOGIST";

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dermatologist_employment_mapping",
            joinColumns = {@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dermatologist_employment_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "pharmacy_id")
    private Map<Pharmacy, Employment> pharmacies;

    public Dermatologist() {
        super();
    }

    public Dermatologist(String email, String password, String name, String surname, Map<Pharmacy, Employment> pharmacies) {
        super(email, password, name, surname);
        this.name = name;
        this.surname = surname;
        this.pharmacies = pharmacies;
    }

    public Map<Pharmacy, Employment> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Map<Pharmacy, Employment> pharmacies) {
        this.pharmacies = pharmacies;
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
