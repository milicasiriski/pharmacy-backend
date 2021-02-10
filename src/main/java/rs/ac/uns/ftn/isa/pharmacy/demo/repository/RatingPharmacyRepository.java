package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.RatingPharmacy;

import java.util.Optional;

public interface RatingPharmacyRepository extends CrudRepository<RatingPharmacy, Long> {
    @Query(value = "SELECT rp.id, rp.pharmacy_id, r.rating, r.patient_id\n" +
            "\tFROM rating_pharmacy AS rp, rating AS r WHERE rp.id = r.id\n" +
            "\tAND r.patient_id = :patientId AND rp.pharmacy_id = :pharmacyId", nativeQuery = true)
    Optional<RatingPharmacy> findByPatientIdAndPharmacyId(@Param("patientId") long patientId, @Param("pharmacyId") long pharmacyId);

    @Query(value = "SELECT AVG(rating) FROM rating_pharmacy AS rp, rating AS r " +
            "WHERE rp.id = r.id AND rp.pharmacy_id=:pharmacyId", nativeQuery = true)
    Optional<Double> findAverageRatingForPharmacy(@Param("pharmacyId") long pharmacyId);
}
