package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;

import java.util.List;

public interface ExamRepository extends CrudRepository<Exam, Long> {

    @Query(nativeQuery = true)
    Iterable<ExamDetails> getDermatologistExamDetails(@Param("patientId") long patientId);

    @Query(nativeQuery = true)
    Iterable<ExamDetails> getPharmacistExamDetails(@Param("patientId") long patientId);

    @Query(value = "SELECT  id, price, status, end_time, start_time, patient_id, pharmacist_id, employment_id FROM exam where pharmacist_id = :pharmacistId", nativeQuery = true)
    List<Exam> getExamByPharmacist(@Param("pharmacistId") long pharmacistId);

    @Query(value = "SELECT  id, price, status, end_time, start_time, patient_id, pharmacist_id, employment_id FROM exam where employment_id = :employmentId", nativeQuery = true)
    List<Exam> getExamByDermatologistEmployment(@Param("employmentId") long employmentId);
}
