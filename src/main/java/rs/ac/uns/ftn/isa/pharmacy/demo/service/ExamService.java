package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistExamDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExamAndDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.ExamSortType;

import javax.mail.MessagingException;

public interface ExamService {
    void createExam(PharmacyAdminExamDto pharmacyAdminExamDto);
    void createAndScheduleExamForDermatologist(DermatologistExamDTO dermatologistExamDTO);

    void scheduleDermatologistExam(long examId, Patient patient) throws MessagingException;
    void scheduleDermatologistExamForPatient(long examId, String patientID) throws MessagingException;

    boolean isExamAvailable(long examId);

    Iterable<ExamAndDermatologistDto> getAvailableDermatologistExamsForPharmacy(long pharmacyId, ExamSortType sortType);

    Iterable<ExamAndDermatologistDto> getAvailableDermatologistExams(ExamSortType sortType);

    Iterable<ExamDetails> getScheduledDermatologistExamsForPatient(Patient patient);

    Iterable<ExamDetails> getDermatologistExamHistoryForPatient(Patient patient);

    void cancelDermatologistExam(long examId, Patient signedInUser);

    void deleteExam(long examId);
}