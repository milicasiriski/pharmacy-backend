package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.RatingMedicine;

import java.util.Optional;

public interface RatingMedicineRepository extends CrudRepository<RatingMedicine, Long> {
    @Query(value = "SELECT rm.id, rm.medicine_id, r.rating, r.patient_id\n" +
            "\tFROM rating_medicine AS rm, rating AS r WHERE rm.id = r.id\n" +
            "\tAND r.patient_id = :patientId AND rm.medicine_id = :medicineId", nativeQuery = true)
    Optional<RatingMedicine> findByPatientIdAndMedicineId(@Param("patientId") long patientId, @Param("medicineId") long medicineId);

    @Query(value = "SELECT AVG(rating) FROM rating_medicine AS rm, rating AS r " +
            "WHERE rm.id = r.id AND rm.medicine_id=:medicineId", nativeQuery = true)
    Optional<Double> findAverageRatingForMedicine(@Param("medicineId") long medicineId);
}
