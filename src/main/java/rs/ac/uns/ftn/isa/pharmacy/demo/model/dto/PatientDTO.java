package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;

public class PatientDTO {

    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
    private String password;

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
        Patient patient = new Patient();

        patient.setName(this.name);
        patient.setSurname(this.surname);
        patient.setAddress(this.address);
        patient.setCity(this.city);
        patient.setCountry(this.country);
        patient.setPhoneNumber(this.phoneNumber);
        patient.setEmail(this.email);
        patient.Disable();

        return patient;
    }

}
