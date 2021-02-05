package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("PHARMACY_ADMIN")
public class PharmacyAdmin extends User {

    private transient final String administrationRole = "ROLE_PHARMACY_ADMINISTRATOR";

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    public PharmacyAdmin() {
        super();
    }

    public PharmacyAdmin(String email, String password, Pharmacy pharmacy, String name, String surname) {
        super(email, password, name, surname, new Address());
        this.pharmacy = pharmacy;
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PharmacyAdmin that = (PharmacyAdmin) o;
        return Objects.equals(administrationRole, that.administrationRole) &&
                Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole, pharmacy);
    }
}
