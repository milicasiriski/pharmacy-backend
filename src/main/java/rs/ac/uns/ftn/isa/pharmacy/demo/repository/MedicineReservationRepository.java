package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineReservation;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;

import java.util.List;

public interface MedicineReservationRepository extends CrudRepository<MedicineReservation, Long> {

    Iterable<MedicineReservation> findByPatient(Patient patient);

    @Query(value = "SELECT id, expiration_date, unique_number, medicine_id, patient_id, pharmacy_id FROM medicine_reservation where medicine_id = :medicineId and pharmacy_id = :pharmacyId", nativeQuery = true)
    List<MedicineReservation> getReservationByPharmacyAndMedicine(@Param("medicineId") Long medicineId, @Param("pharmacyId") Long pharmacyId);

    MedicineReservation findByUniqueNumber(String uniqueNumber);
}
