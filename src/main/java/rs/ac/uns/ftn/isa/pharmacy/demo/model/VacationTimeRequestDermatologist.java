package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import java.util.Objects;

public class VacationTimeRequestDermatologist extends VacationTimeRequest {

    private Dermatologist dermatologist;

    public VacationTimeRequestDermatologist() {

    }

    public VacationTimeRequestDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public VacationTimeRequestDermatologist(TimeInterval requestedTimeForVacation, boolean isApproved, String rejectedReason, Dermatologist dermatologist) {
        super(requestedTimeForVacation, isApproved, rejectedReason);
        this.dermatologist = dermatologist;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VacationTimeRequestDermatologist that = (VacationTimeRequestDermatologist) o;
        return dermatologist.equals(that.dermatologist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dermatologist);
    }

    @Override
    public String toString() {
        return "VacationTimeRequestDermatologist{" +
                "dermatologist=" + dermatologist +
                ", requestedTimeForVacation=" + requestedTimeForVacation +
                ", isApproved=" + isApproved +
                ", rejectedReason='" + rejectedReason + '\'' +
                '}';
    }
}
