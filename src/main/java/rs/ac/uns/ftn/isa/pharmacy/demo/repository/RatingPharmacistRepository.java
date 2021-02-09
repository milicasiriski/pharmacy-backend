package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.RatingPharmacist;

import java.util.Optional;

public interface RatingPharmacistRepository extends CrudRepository<RatingPharmacist, Long> {
    @Query(value = "SELECT rp.id, rp.pharmacist_id, r.rating, r.patient_id\n" +
            "\tFROM rating_pharmacist AS rp, rating AS r WHERE rp.id = r.id\n" +
            "\tAND r.patient_id = :patientId AND rp.pharmacist_id = :pharmacistId", nativeQuery = true)
    Optional<RatingPharmacist> findByPatientIdAndPharmacistId(@Param("patientId") long patientId, @Param("pharmacistId") long pharmacistId);
}
