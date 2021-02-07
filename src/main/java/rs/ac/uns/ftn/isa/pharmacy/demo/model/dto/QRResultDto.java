package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class QRResultDto implements Serializable {

    private int bill;
    private String address;
    private String pharmacyName;
    private EPrescriptionDto prescription;
    private Long pharmacyId;
    private double pharmacyRating;

    public QRResultDto(int bill, String address, String pharmacyName, EPrescriptionDto prescription, Long pharmacyId, double pharmacyRating) {
        this.bill = bill;
        this.address = address;
        this.pharmacyName = pharmacyName;
        this.prescription = prescription;
        this.pharmacyId = pharmacyId;
        this.pharmacyRating = pharmacyRating;
    }

    public QRResultDto() {
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public double getPharmacyRating() {
        return pharmacyRating;
    }

    public void setPharmacyRating(double pharmacyRating) {
        this.pharmacyRating = pharmacyRating;
    }

    public EPrescriptionDto getPrescription() {
        return prescription;
    }

    public void setPrescription(EPrescriptionDto prescription) {
        this.prescription = prescription;
    }
}
