package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.RatingDermatologist;

import java.util.Optional;

public interface RatingDermatologistRepository extends CrudRepository<RatingDermatologist, Long> {
    @Query(value = "SELECT rd.id, rd.dermatologist_id, r.rating, r.patient_id\n" +
            "\tFROM rating_dermatologist AS rd, rating AS r WHERE rd.id = r.id\n" +
            "\tAND r.patient_id = :patientId AND rd.dermatologist_id = :dermatologistId", nativeQuery = true)
    Optional<RatingDermatologist> findByPatientIdAndDermatologistId(@Param("patientId") long patientId, @Param("dermatologistId") long dermatologistId);
}
