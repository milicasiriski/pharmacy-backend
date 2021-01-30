package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Objects;

public class LogInDto {

    private String email;
    private String password;

    public LogInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LogInDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogInDto logInDto = (LogInDto) o;
        return Objects.equals(email, logInDto.email) &&
                Objects.equals(password, logInDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "LogInDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
