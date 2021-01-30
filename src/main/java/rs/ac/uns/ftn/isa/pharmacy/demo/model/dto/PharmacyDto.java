package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import javax.persistence.Column;
import java.util.Objects;

public class PharmacyDto {

    private String name;
    private String address;
    private String about;
    private Long id;

    public PharmacyDto(String name, String address, String about, Long id) {
        this.name = name;
        this.address = address;
        this.about = about;
        this.id = id;
    }

    public PharmacyDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyDto that = (PharmacyDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(about, that.about) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, about, id);
    }

    @Override
    public String toString() {
        return "PharmacyDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", about='" + about + '\'' +
                ", id=" + id +
                '}';
    }
}
