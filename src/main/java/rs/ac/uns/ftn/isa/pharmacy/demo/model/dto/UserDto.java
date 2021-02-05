package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class UserDto {

    private AddressDto address;
    private String name;
    private String surname;

    public UserDto() {

    }

    public UserDto(AddressDto address, String name, String surname) {
        this.address = address;
        this.name = name;
        this.surname = surname;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
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
}