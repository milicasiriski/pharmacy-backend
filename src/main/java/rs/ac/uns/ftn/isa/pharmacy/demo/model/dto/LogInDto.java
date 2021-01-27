package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.UserType;

import java.util.Objects;

public class LogInDto {

    private String email;
    private String password;
    private String name;
    private String surname;
    private UserType type;

    public LogInDto() {

    }

    public LogInDto(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public LogInDto(String email, String password, String name, String surname, UserType type) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setType(type);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogInDto logInDto = (LogInDto) o;
        return Objects.equals(email, logInDto.email) &&
                Objects.equals(password, logInDto.password) &&
                Objects.equals(name, logInDto.name) &&
                Objects.equals(surname, logInDto.surname) &&
                type == logInDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, surname, type);
    }

    @Override
    public String toString() {
        return "LogInDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type=" + type +
                '}';
    }
}
