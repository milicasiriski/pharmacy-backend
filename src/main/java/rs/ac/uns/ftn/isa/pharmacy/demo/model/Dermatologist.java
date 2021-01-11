package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DERMATOLOGIST")
public class Dermatologist extends User {
    private final String administrationRole = "ROLE_DERMATOLOGIST";

    private transient final String administrationRole = "ROLE_DERMATOLOGIST";

    public Dermatologist() {
        super();
    }

    public Dermatologist(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Dermatologist{" +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
