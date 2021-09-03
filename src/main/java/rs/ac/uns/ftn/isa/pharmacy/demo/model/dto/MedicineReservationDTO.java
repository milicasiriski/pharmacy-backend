package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineReservation;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class MedicineReservationDTO implements Serializable {
    private long reservationId;
    private String medicineName;
    private String medicineForm;
    private String pharmacyName;
    private String pharmacyAddress;
    private String deadline;
    private boolean cancellable;
    private String uniqueNumber;

    public MedicineReservationDTO() {
    }

    public MedicineReservationDTO(long reservationId, String medicineName, String medicineForm, String pharmacyName, String pharmacyAddress, String deadline, boolean cancellable, String uniqueNumber) {
        this.reservationId = reservationId;
        this.medicineName = medicineName;
        this.medicineForm = medicineForm;
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
        this.deadline = deadline;
        this.cancellable = cancellable;
        this.uniqueNumber = uniqueNumber;
    }

    public MedicineReservationDTO(MedicineReservation medicineReservation, boolean cancellable) {
        this.reservationId = medicineReservation.getId();
        this.medicineName = medicineReservation.getMedicine().getName();
        this.medicineForm = medicineReservation.getMedicine().getForm().label;
        this.pharmacyName = medicineReservation.getPharmacy().getName();
        this.pharmacyAddress = medicineReservation.getPharmacy().getAddress().getStreet();
        this.deadline = new SimpleDateFormat("dd/MM/yyyy").format(medicineReservation.getExpirationDate().getTime());
        this.cancellable = cancellable;
        this.uniqueNumber = medicineReservation.getUniqueNumber();
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineForm() {
        return medicineForm;
    }

    public void setMedicineForm(String medicineForm) {
        this.medicineForm = medicineForm;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }
}
