package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class VacationDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String vacationInterval;
    private String role;
    private String reason;
    private String status;

    public VacationDto() {
    }

    public VacationDto(Long id, String name, String surname, String vacationInterval, String role, String reason, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.vacationInterval = vacationInterval;
        this.role = role;
        this.reason = reason;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
