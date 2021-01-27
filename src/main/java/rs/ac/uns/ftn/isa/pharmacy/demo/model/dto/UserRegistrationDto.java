package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.UserType;

import java.util.Objects;

public class UserRegistrationDto {

    private String email;
    private String password;
    private String name;
    private String surname;
    private UserType type;
    private Long pharmacyId;

    public UserRegistrationDto(String email, String password, String name, String surname, UserType type, Long pharmacyId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.pharmacyId = pharmacyId;
    }

    public UserRegistrationDto() {
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationDto that = (UserRegistrationDto) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                type == that.type &&
                Objects.equals(pharmacyId, that.pharmacyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, surname, type, pharmacyId);
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type=" + type +
                ", pharmacyId=" + pharmacyId +
                '}';
    }
}
