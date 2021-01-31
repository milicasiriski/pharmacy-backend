package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("SUPPLIER")
public class Supplier extends User {

    private transient final String administrationRole = "ROLE_SUPPLIER";

    public Supplier() {
    }

    public Supplier(String email, String password, String name, String surname) {
        super(email, password, name, surname);
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(administrationRole, supplier.administrationRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "administrationRole='" + administrationRole + '\'' +
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
