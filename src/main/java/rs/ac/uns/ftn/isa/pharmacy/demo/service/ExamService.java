package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExamAndDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.ExamSortType;

import javax.mail.MessagingException;

public interface ExamService {
    void createExam(PharmacyAdminExamDto pharmacyAdminExamDto);

    void scheduleDermatologistExam(long examId, Patient patient) throws MessagingException;

    boolean isExamAvailable(long examId);

    Iterable<ExamAndDermatologistDto> getAvailableDermatologistExamsForPharmacy(long pharmacyId, ExamSortType sortType);

    Iterable<ExamDetails> getDermatologistExamsForPatient(Patient patient);
}