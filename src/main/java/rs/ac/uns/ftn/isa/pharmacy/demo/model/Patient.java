package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    private transient final String administrationRole = "ROLE_PATIENT";

    @Column(name = "patient_phone_num")
    private String phoneNumber;

    @Column(name = "patient_activation_code", length = 64)
    private String activationCode;

    @Column(name = "patient_penalty_points")
    private int penaltyPoints;

    @Column(name = "patient_loyalty_points")
    private int loyaltyPoints;

    public Patient() {
        super();
    }

    public Patient(String email, String password, String name, String surname, String phoneNumber, int penaltyPoints, Address address, int loyaltyPoints) {
        super(email, password, name, surname, address);
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.penaltyPoints = penaltyPoints;
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public void addPenaltyPoints(int points) {
        this.penaltyPoints += points;
    }

    public void subtractPenaltyPoints(int points) {
        this.penaltyPoints -= points;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public void subtractLoyaltyPoints(int points) {
        this.loyaltyPoints -= points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return penaltyPoints == patient.penaltyPoints &&
                Objects.equals(administrationRole, patient.administrationRole) &&
                Objects.equals(phoneNumber, patient.phoneNumber) &&
                Objects.equals(activationCode, patient.activationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole, phoneNumber, activationCode, penaltyPoints);
    }
}
