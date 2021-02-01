package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExamDetails;

public interface ExamRepository extends CrudRepository<Exam, Long> {

    @Query(nativeQuery=true)
    Iterable<ExamDetails> getExamDetails(@Param("patientId") long patientId);
}
