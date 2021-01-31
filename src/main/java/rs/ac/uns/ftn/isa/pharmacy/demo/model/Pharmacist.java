package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("PHARMACIST")
public class Pharmacist extends User {

    private transient final String administrationRole = "ROLE_PHARMACIST";

    @ManyToMany(mappedBy = "pharmacists")
    private List<Pharmacy> pharmacies;

    public Pharmacist() {
        super();
    }

    public Pharmacist(String email, String password, String name, String surname, List<Pharmacy> pharmacies) {
        super(email, password, name, surname);
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
        Pharmacist that = (Pharmacist) o;
        return Objects.equals(administrationRole, that.administrationRole) &&
                Objects.equals(pharmacies, that.pharmacies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole, pharmacies);
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "administrationRole='" + administrationRole + '\'' +
                ", pharmacies=" + pharmacies +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", name=" + name +
                ", enabled=" + enabled +
                '}';
    }
}
