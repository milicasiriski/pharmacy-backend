package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Date;

public class MedicineReservationEmailParams {
    private String medicineName;
    private Date deadline;
    private String pharmacyName;
    private String pharmacyAddress;
    private String uniqueReservationNumber;

    public MedicineReservationEmailParams() {
    }

    public MedicineReservationEmailParams(String medicineName, Date deadline, String pharmacyName, String pharmacyAddress, String uniqueReservationNumber) {
        this.medicineName = medicineName;
        this.deadline = deadline;
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
        this.uniqueReservationNumber = uniqueReservationNumber;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public String getUniqueReservationNumber() {
        return uniqueReservationNumber;
    }

    public void setUniqueReservationNumber(String uniqueReservationNumber) {
        this.uniqueReservationNumber = uniqueReservationNumber;
    }
}
