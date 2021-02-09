package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.UserType;

import java.util.List;

public class UserRegistrationDto {

    private String email;
    private String password;
    private String name;
    private String surname;
    private UserType type;
    private Long pharmacyId;
    private AddressDto address;
    private List<TimeIntervalDto> shifts;

    public UserRegistrationDto(String email, String password, String name, String surname, UserType type, Long pharmacyId, AddressDto address, List<TimeIntervalDto> shifts) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.pharmacyId = pharmacyId;
        this.address = address;
        this.shifts = shifts;
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<TimeIntervalDto> getShifts() {
        return shifts;
    }

    public void setShifts(List<TimeIntervalDto> shifts) {
        this.shifts = shifts;
    }
}
