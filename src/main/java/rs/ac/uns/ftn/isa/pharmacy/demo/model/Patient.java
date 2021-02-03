package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    private transient final String administrationRole = "ROLE_PATIENT";

    @Column(name = "patient_address")
    private String address;

    @Column(name = "patient_city")
    private String city;

    @Column(name = "patient_country")
    private String country;

    @Column(name = "patient_phone_num")
    private String phoneNumber;

    @Column(name = "patient_activation_code", length = 64)
    private String activationCode;

    @Column(name = "patient_penalty_points")
    private Integer penaltyPoints;

    public Patient() {
        super();
    }

    public Patient(String email, String password, String name, String surname, String address, String city, String country, String phoneNumber, Integer penaltyPoints) {
        super(email, password, name, surname);
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.penaltyPoints = penaltyPoints;
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(address, patient.address) &&
                Objects.equals(city, patient.city) &&
                Objects.equals(country, patient.country) &&
                Objects.equals(phoneNumber, patient.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, city, country, phoneNumber);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "administrationRole='" + administrationRole + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", activationCode='" + activationCode + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
