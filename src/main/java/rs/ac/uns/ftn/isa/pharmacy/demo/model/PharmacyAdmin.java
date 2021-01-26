package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue("PHARMACY_ADMIN")
public class PharmacyAdmin extends User implements Serializable {

    private transient final String administrationRole = "ROLE_PHARMACY_ADMINISTRATOR";

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    public PharmacyAdmin() {
        super();
    }

    public PharmacyAdmin(String email, String password, Pharmacy pharmacy) {
        super(email, password);
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
    public boolean isEnabled() {
        return true;
    }
}
