package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;

public interface DermatologistVacationRepository extends CrudRepository<VacationTimeRequestDermatologist, Long> {

    @Query(value = "SELECT version, vacation_request_dermatologist.id, dermatologist_id, pharmacy_id, rejected_reason, time_end, time_start, status FROM vacation_request_dermatologist, vacation_time_request WHERE vacation_time_request.id = vacation_request_dermatologist.id AND pharmacy_id = :pharmacyId", nativeQuery = true)
    Iterable<VacationTimeRequestDermatologist> findVacationsByPharmacyId(@Param("pharmacyId") Long pharmacyId);

    @Query(value = "SELECT version, vacation_request_dermatologist.id, dermatologist_id, pharmacy_id, rejected_reason, time_end, time_start, status FROM vacation_request_dermatologist, vacation_time_request WHERE vacation_time_request.id = vacation_request_dermatologist.id AND vacation_request_dermatologist.dermatologist_id = :dermatologistId and status = 1", nativeQuery = true)
    Iterable<VacationTimeRequestDermatologist> findApprovedVacationsByDermatologistId(@Param("dermatologistId") Long dermatologistId);

}
