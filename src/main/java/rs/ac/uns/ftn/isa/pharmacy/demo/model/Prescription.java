package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @SequenceGenerator(name = "prescription_sequence_generator", sequenceName = "prescription_sequence", initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_sequence_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "prescription_medicine_mapping", joinColumns = @JoinColumn(name = "prescription_id"))
    @MapKeyJoinColumn(name = "medicine_id", referencedColumnName = "id")
    @Column(name = "prescription_medicine_amount")
    private Map<Medicine, Integer> medicines;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "prescription_date")
    private Calendar prescriptionDate;

    public Prescription() {
    }

    public Prescription(Long id, Patient patient, Map<Medicine, Integer> medicines, Calendar prescriptionDate) {
        this.id = id;
        this.patient = patient;
        this.medicines = medicines;
        this.prescriptionDate = prescriptionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Map<Medicine, Integer> getMedicines() {
        return medicines;
    }

    public void setMedicines(Map<Medicine, Integer> medicines) {
        this.medicines = medicines;
    }

    public Calendar getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Calendar prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getName(){
        return patient.getName();
    }

    public String getSurname(){
        return patient.getSurname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(medicines, that.medicines) &&
                Objects.equals(prescriptionDate, that.prescriptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, medicines, prescriptionDate);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", patient=" + patient +
                ", medicines=" + medicines +
                ", prescriptionDate=" + prescriptionDate +
                '}';
    }
}
