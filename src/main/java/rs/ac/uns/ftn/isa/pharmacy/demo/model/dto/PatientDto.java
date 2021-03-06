package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Address;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;

public class PatientDto {

    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
    private String password;

    public PatientDto() {

    }

    public PatientDto(String name, String surname, String address, String city, String country, String phoneNumber, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public PatientDto(Patient patient) {
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.address = patient.getAddress().getStreet();
        this.city = patient.getAddress().getCity();
        this.country = patient.getAddress().getCountry();
        this.phoneNumber = patient.getPhoneNumber();
        this.email = patient.getEmail();
        this.password = patient.getPassword();
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Patient createPatient() {
        Patient patient = new Patient(this.email, this.password, this.name, this.surname,
                this.phoneNumber, 0, new Address(this.country, this.address, this.city), 0);
        patient.Disable();

        return patient;
    }

}
