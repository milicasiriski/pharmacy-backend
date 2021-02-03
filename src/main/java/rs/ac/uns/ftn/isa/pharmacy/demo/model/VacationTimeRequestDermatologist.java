package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.VacationStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacation_request_dermatologist")
public class VacationTimeRequestDermatologist extends VacationTimeRequest {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "dermatologist_id")
    private Dermatologist dermatologist;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    public VacationTimeRequestDermatologist() {

    }

    public VacationTimeRequestDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public VacationTimeRequestDermatologist(TimeInterval requestedTimeForVacation, VacationStatus status, String rejectedReason, Dermatologist dermatologist, Pharmacy pharmacy) {
        super(requestedTimeForVacation, status, rejectedReason);
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
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
        VacationTimeRequestDermatologist that = (VacationTimeRequestDermatologist) o;
        return Objects.equals(dermatologist, that.dermatologist) &&
                Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dermatologist, pharmacy);
    }
}
