package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExamAndDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;

public interface ExamService {
    void createExam(PharmacyAdminExamDto pharmacyAdminExamDto);

    void scheduleDermatologistExam(long examId, Patient patient);

    boolean isExamAvailable(long examId);

    Iterable<ExamAndDermatologistDto> getAvailableDermatologistExamsForPharmacy(long pharmacyId);
}