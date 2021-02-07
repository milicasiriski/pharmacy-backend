package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;

public interface ExamRepository extends CrudRepository<Exam, Long> {

    @Query(nativeQuery=true)
    Iterable<ExamDetails> getDermatologistExamDetails(@Param("patientId") long patientId);

    @Query(nativeQuery=true)
    Iterable<ExamDetails> getPharmacistExamDetails(@Param("patientId") long patientId);
}
