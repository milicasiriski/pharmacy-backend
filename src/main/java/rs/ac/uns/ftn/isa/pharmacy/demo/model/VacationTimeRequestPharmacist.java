package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
@Table(name = "vacation_request_pharmacist")
public class VacationTimeRequestPharmacist extends VacationTimeRequest {

    @Transient
    private Pharmacist pharmacist;

    public VacationTimeRequestPharmacist() {

    }

    public VacationTimeRequestPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public VacationTimeRequestPharmacist(TimeInterval requestedTimeForVacation, boolean isApproved, String rejectedReason, Pharmacist pharmacist) {
        super(requestedTimeForVacation, isApproved, rejectedReason);
        this.pharmacist = pharmacist;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VacationTimeRequestPharmacist that = (VacationTimeRequestPharmacist) o;
        return pharmacist.equals(that.pharmacist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pharmacist);
    }

    @Override
    public String toString() {
        return "VacationTimeRequestPharmacist{" +
                "pharmacist=" + pharmacist +
                ", requestedTimeForVacation=" + requestedTimeForVacation +
                ", isApproved=" + isApproved +
                ", rejectedReason='" + rejectedReason + '\'' +
                '}';
    }
}
