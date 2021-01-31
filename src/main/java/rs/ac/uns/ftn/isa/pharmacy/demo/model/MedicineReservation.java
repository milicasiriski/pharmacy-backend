package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "medicine_reservation")
public class MedicineReservation {

    @Id
    @SequenceGenerator(name = "medicine_reservation_sequence_generator", sequenceName = "medicine_reservation_sequence", initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_reservation_sequence_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @Column(name = "expiration_date")
    private Calendar expirationDate;

    @Column(name = "unique_number")
    private String uniqueNumber;

    public MedicineReservation() {
    }

    public MedicineReservation(Medicine medicine, Patient patient, Pharmacy pharmacy, Calendar expirationDate, String uniqueNumber) {
        this.medicine = medicine;
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.expirationDate = expirationDate;
        this.uniqueNumber = uniqueNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }
}
