package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class PharmacyNameAndAddressDto implements Serializable {

    private String pharmacyName;
    private String pharmacyAddress;

    public PharmacyNameAndAddressDto() {

    }

    public PharmacyNameAndAddressDto(String pharmacyName, String pharmacyAddress) {
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyNameAndAddressDto that = (PharmacyNameAndAddressDto) o;
        return Objects.equals(pharmacyName, that.pharmacyName) &&
                Objects.equals(pharmacyAddress, that.pharmacyAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacyName, pharmacyAddress);
    }

    @Override
    public String toString() {
        return "PharmacyNameAndAddressDto{" +
                "pharmacyName='" + pharmacyName + '\'' +
                ", pharmacyAddress='" + pharmacyAddress + '\'' +
                '}';
    }
}
