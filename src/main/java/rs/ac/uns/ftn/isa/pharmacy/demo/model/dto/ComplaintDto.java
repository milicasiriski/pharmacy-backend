package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.ComplaintSubject;

import java.io.Serializable;

public class ComplaintDto implements Serializable {

    private ComplaintSubject subjectType;
    private Long id;
    private Long staffId;
    private String staffName;
    private String staffSurname;
    private Long patientId;
    private String patientName;
    private String patientSurname;
    private String complaintText;
    private Long pharmacyId;
    private String pharmacyName;

    public ComplaintDto(Complaint complaint) {
        this.id = complaint.getId();
        Patient patient = complaint.getPatient();
        this.patientId = patient.getId();
        this.patientName = patient.getName();
        this.patientSurname = patient.getSurname();
        this.complaintText = complaint.getComplaintText();

        if (complaint.getPharmacy() != null) {
            this.subjectType = ComplaintSubject.PHARMACY;
            this.pharmacyId = complaint.getPharmacy().getId();
            this.pharmacyName = complaint.getPharmacy().getName();
        } else {
            User staffMember = complaint.getStaffMember();
            this.staffName = staffMember.getName();
            this.staffId = staffMember.getId();
            this.staffSurname = staffMember.getSurname();
            if (complaint.getStaffMember().getClass() == Dermatologist.class) {
                this.subjectType = ComplaintSubject.DERMATOLOGIST;
            } else {
                this.subjectType = ComplaintSubject.PHARMACIST;
            }
        }
    }

    public ComplaintDto() {
    }

    public ComplaintSubject getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(ComplaintSubject subjectType) {
        this.subjectType = subjectType;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffSurname() {
        return staffSurname;
    }

    public void setStaffSurname(String staffSurname) {
        this.staffSurname = staffSurname;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
