package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class VacationDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String vacationInterval;
    private String role;
    private boolean approved;
    private String reason;
    private String status;

    public VacationDto() {
    }

    public VacationDto(Long id, String name, String surname, String vacationInterval, String role, boolean approved, String reason, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.vacationInterval = vacationInterval;
        this.role = role;
        this.approved = approved;
        this.reason = reason;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        return approved == that.approved &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(vacationInterval, that.vacationInterval) &&
                Objects.equals(role, that.role) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, vacationInterval, role, approved, reason, status);
    }

    @Override
    public String toString() {
        return "VacationDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", vacationInterval='" + vacationInterval + '\'' +
                ", role='" + role + '\'' +
                ", approved=" + approved +
                ", reason='" + reason + '\'' +
                '}';
    }
}
