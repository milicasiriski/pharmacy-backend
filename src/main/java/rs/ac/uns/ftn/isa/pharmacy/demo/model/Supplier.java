package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("SUPPLIER")
public class Supplier extends User {

    private transient final String administrationRole = "ROLE_SUPPLIER";

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "supplier_medicine_mapping", joinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "medicine_id", referencedColumnName = "id")
    @Column(name = "medicine_amount")
    private Map<Medicine, Integer> medicineAmount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Set<Offer> offers;

    public Supplier() {
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    public Map<Medicine, Integer> getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(Map<Medicine, Integer> medicineAmount) {
        this.medicineAmount = medicineAmount;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(administrationRole, supplier.administrationRole) &&
                Objects.equals(medicineAmount, supplier.medicineAmount) &&
                Objects.equals(offers, supplier.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole, medicineAmount, offers);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "administrationRole='" + administrationRole + '\'' +
                ", medicineAmount=" + medicineAmount +
                ", offers=" + offers +
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
