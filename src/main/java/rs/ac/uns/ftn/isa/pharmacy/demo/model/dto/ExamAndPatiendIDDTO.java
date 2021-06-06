package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class ExamAndPatiendIDDTO {
    private String examID;
    private String patientID;

    public ExamAndPatiendIDDTO() {
    }

    public ExamAndPatiendIDDTO(String examID, String patientID) {
        this.patientID = patientID;
        this.examID = examID;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientID() {
        return patientID;
    }
}
