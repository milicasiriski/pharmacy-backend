package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;

public class ExamAndDermatologistDto {
    private Exam exam;
    private Dermatologist dermatologist;

    public ExamAndDermatologistDto() {

    }

    public ExamAndDermatologistDto(Exam exam, Dermatologist dermatologist) {
        this.exam = exam;
        this.dermatologist = dermatologist;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
}
