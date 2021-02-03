package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.VacationStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacation_request_pharmacist")
public class VacationTimeRequestPharmacist extends VacationTimeRequest {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    public VacationTimeRequestPharmacist() {

    }

    public VacationTimeRequestPharmacist(TimeInterval requestedTimeForVacation, VacationStatus status, String rejectedReason) {
        super(requestedTimeForVacation, status, rejectedReason);
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
        return Objects.equals(pharmacist, that.pharmacist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pharmacist);
    }
}
