package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("DERMATOLOGIST")
public class Dermatologist extends User implements Serializable {

    private transient final String administrationRole = "ROLE_DERMATOLOGIST";

    @ManyToMany(mappedBy = "dermatologists")
    private List<Pharmacy> pharmacies;

    public Dermatologist() {
        super();
    }

    public Dermatologist(String email, String password, String name, String surname, List<Pharmacy> pharmacies) {
        super(email, password, name, surname);
        this.name = name;
        this.surname = surname;
        this.pharmacies = pharmacies;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
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
