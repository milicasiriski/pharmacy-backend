package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;

import java.util.Calendar;

public interface PharmacistVacationRepository extends CrudRepository<VacationTimeRequestPharmacist, Long> {
    @Query(value = "SELECT version, vrp.id, pharmacist_id, rejected_reason, time_end, time_start, status \n" +
            "\tFROM public.vacation_request_pharmacist AS vrp, public.vacation_time_request AS vr\n" +
            "\tWHERE vrp.id = vr.id AND status = 1 AND pharmacist_id = :pharmacistId AND :day BETWEEN time_start AND time_end", nativeQuery = true)
    Iterable<VacationTimeRequestPharmacist> findApprovedVacationRequestsForPharmacistOnDay(@Param("pharmacistId") long pharmacistId, @Param("day") Calendar day);

    @Query(value = "SELECT vr.version, vrp.id, pharmacist_id, rejected_reason, time_end, time_start, status\n" +
            " FROM public.vacation_request_pharmacist AS vrp, vacation_time_request AS vr, pharmacy_user AS p WHERE vrp.pharmacist_id = p.id" +
            " AND p.pharmacy_id = :pharmacyId AND p.user_type = 'PHARMACIST' AND vrp.id = vr.id", nativeQuery = true)
    Iterable<VacationTimeRequestPharmacist> findVacationRequestsForPharmacistByPharmacy(@Param("pharmacyId") long pharmacyId);
}
