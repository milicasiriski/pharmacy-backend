package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@DiscriminatorValue("SYSTEM_ADMIN")
public class SystemAdmin extends User implements Serializable {

    private transient final String administrationRole = "ROLE_SYSTEM_ADMINISTRATOR";

    public SystemAdmin() {
    }

    public SystemAdmin(String email, String password, String name, String surname) {
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
        SystemAdmin that = (SystemAdmin) o;
        return Objects.equals(administrationRole, that.administrationRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole);
    }

    @Override
    public String toString() {
        return "SystemAdmin{" +
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
