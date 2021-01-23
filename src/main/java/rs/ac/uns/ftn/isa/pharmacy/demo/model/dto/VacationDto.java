package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Objects;

public class VacationDto {

    private String name;
    private String surname;
    private String vacationInterval;
    private String role;

    public VacationDto() {

    }

    public VacationDto(String name, String surname, String vacationInterval, String role) {
        this.name = name;
        this.surname = surname;
        this.vacationInterval = vacationInterval;
        this.role = role;
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

    public String getVacationInterval() {
        return vacationInterval;
    }

    public void setVacationInterval(String vacationInterval) {
        this.vacationInterval = vacationInterval;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacationDto that = (VacationDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(vacationInterval, that.vacationInterval) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, vacationInterval, role);
    }

    @Override
    public String toString() {
        return "VacationDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", vacationInterval='" + vacationInterval + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
