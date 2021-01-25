package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("PHARMACY_ADMIN")
public class PharmacyAdmin extends User implements Serializable {

    private transient final String administrationRole = "ROLE_PHARMACY_ADMINISTRATOR";

    @ManyToMany(mappedBy = "pharmacyAdmins")
    private List<Pharmacy> pharmacies;

    public PharmacyAdmin() {
        super();
    }

    public PharmacyAdmin(String email, String password, String name, String surname, List<Pharmacy> pharmacies) {
        super(email, password);
        this.pharmacies = pharmacies;
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
