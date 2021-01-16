package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.Column;
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

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToMany(mappedBy = "dermatologists")
    private List<Pharmacy> pharmacies;

    public Dermatologist() {
        super();
    }

    public Dermatologist(String email, String password, String name, String surname, List<Pharmacy> pharmacies) {
        super(email, password);
        this.name = name;
        this.surname = surname;
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
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole, name, surname);
    }
}
