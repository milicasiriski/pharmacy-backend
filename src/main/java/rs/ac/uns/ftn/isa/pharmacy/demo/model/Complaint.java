package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COMPLAINT")
public class Complaint {

    @Id
    @SequenceGenerator(name = "complaint_sequence_generator", sequenceName = "complaint_sequence", initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_sequence_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "staff_id")
    private User staffMember;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @Column
    private String complaintText;

    @Column
    private String answerText;

    @Column
    private boolean resolved;

    public Complaint(Patient patient, User staffMember, String complaintText, boolean resolved) {
        this.patient = patient;
        this.staffMember = staffMember;
        this.complaintText = complaintText;
        this.resolved = resolved;
    }

    public Complaint(Patient patient, Pharmacy pharmacy, String complaintText, boolean resolved) {
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.complaintText = complaintText;
        this.resolved = resolved;
    }

    public Complaint() {
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

    public User getStaffMember() {
        return staffMember;
    }

    public void setStaffMember(User stuffMember) {
        this.staffMember = stuffMember;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answer) {
        this.answerText = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return resolved == complaint.resolved &&
                Objects.equals(id, complaint.id) &&
                Objects.equals(patient, complaint.patient) &&
                Objects.equals(staffMember, complaint.staffMember) &&
                Objects.equals(pharmacy, complaint.pharmacy) &&
                Objects.equals(complaintText, complaint.complaintText) &&
                Objects.equals(answerText, complaint.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, staffMember, pharmacy, complaintText, answerText, resolved);
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", patient=" + patient +
                ", staffMember=" + staffMember +
                ", pharmacy=" + pharmacy +
                ", complaintText='" + complaintText + '\'' +
                ", answerText='" + answerText + '\'' +
                ", resolved=" + resolved +
                '}';
    }
}
