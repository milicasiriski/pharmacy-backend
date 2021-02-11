package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;

import java.util.List;

public interface DermatologistRepository extends CrudRepository<Dermatologist, Long> {

    @Query(value = "SELECT city, country, latitude, longitude, street, id, email, password, enabled, last_password_reset_date, user_type, name, surname, rating FROM pharmacy_user WHERE user_type = 'DERMATOLOGIST'", nativeQuery = true)
    Iterable<Dermatologist> getAllDermatologists();

    @Query(value = "SELECT DISTINCT pharmacy_user.version,  pharmacy_user.city, pharmacy_user.country, pharmacy_user.latitude, pharmacy_user.longitude, pharmacy_user.street, pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname, pharmacy_user.rating FROM pharmacy_user JOIN dermatologist_employment_mapping ON pharmacy_user.id = dermatologist_employment_mapping.dermatologist_id JOIN pharmacy ON pharmacy.id = dermatologist_employment_mapping.pharmacy_id WHERE pharmacy.id = :pharmacyId and user_type = 'DERMATOLOGIST'", nativeQuery = true)
    List<Dermatologist> getDermatologistsByPharmacy(@Param("pharmacyId") Long pharmacyId);

    @Query(value = "SELECT DISTINCT d.city, d.country, d.latitude, d.longitude, d.street, d.id, d.email, d.password, " +
            "d.enabled, d.last_password_reset_date, d.user_type, d.name, d.surname, d.rating " +
            "FROM pharmacy_user AS d, exam AS e, dermatologist_employment_mapping AS demap " +
            "WHERE d.user_type = 'DERMATOLOGIST' AND e.employment_id=demap.dermatologist_employment_id " +
            "AND d.id=demap.dermatologist_id AND (e.status = 0 OR e.status = 1) AND e.patient_id=:patientId", nativeQuery = true)
    List<Dermatologist> getByPatientsId(@Param("patientId") long patientId);

    @Query(value = "SELECT case when (count(d.id) > 0) then true else false end\n" +
            "\tFROM pharmacy_user AS d, exam AS e, dermatologist_employment_mapping AS demap\n" +
            "\tWHERE d.user_type = 'DERMATOLOGIST' AND e.employment_id=demap.dermatologist_employment_id\n" +
            "\tAND d.id=demap.dermatologist_id AND (e.status = 0 OR e.status = 1) AND e.patient_id=:patientId" +
            "\tAND d.id = :dermatologistId", nativeQuery = true)
    boolean canPatientRateDermatologist(long patientId, long dermatologistId);
}
