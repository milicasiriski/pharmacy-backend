package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;

import java.util.List;

public interface PharmacistRepository extends CrudRepository<Pharmacist, Long> {

    @Query(value = "SELECT version, id, email, password, enabled, last_password_reset_date, user_type, name, surname, pharmacy_id FROM pharmacy_user WHERE user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getAll();

    @Query(value = "SELECT DISTINCT pharmacy_user.version, pharmacy_user.city, pharmacy_user.country, pharmacy_user.latitude, pharmacy_user.longitude, pharmacy_user.street, pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname, pharmacy_id, pharmacy_user.rating FROM pharmacy_user JOIN pharmacy ON pharmacy.id = pharmacy_id WHERE pharmacy.id = :pharmacyId and user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getByPharmacy(@Param("pharmacyId") Long pharmacyId);

    @Query(value = "SELECT DISTINCT version, id, email, password, enabled, last_password_reset_date, user_type, name, surname, pharmacy_id, rating, " +
            "\tcity, country, latitude, longitude, street" +
            "\tFROM pharmacy_user WHERE pharmacy_id = :pharmacyId and user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> findByPharmacyId(@Param("pharmacyId") long pharmacyId);

    @Query(value = "SELECT version, city, country, latitude, longitude, street, id, email, password, enabled, last_password_reset_date, user_type, name, surname FROM pharmacy_user WHERE user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getAllPharmacists();

    @Query(value = "SELECT DISTINCT version,  pharmacy_user.city, pharmacy_user.country, pharmacy_user.latitude, pharmacy_user.longitude, pharmacy_user.street, pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname FROM pharmacy_user JOIN pharmacy_pharmacists ON pharmacy_user.id = pharmacy_pharmacists.pharmacist_id JOIN pharmacy ON pharmacy.id = pharmacy_pharmacists.pharmacy_id WHERE pharmacy.name = :pharmacyName and user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getPharmacistByPharmacy(@Param("pharmacyName") String pharmacyName);

    @Query(value = "SELECT DISTINCT version, p.city, p.country, p.latitude, p.longitude, p.street, p.id, p.email, p.password, p.enabled, \n" +
            "\tp.last_password_reset_date, p.user_type, p.name, p.surname, p.pharmacy_id, p.rating \n" +
            "\tFROM pharmacy_user AS p, exam AS e WHERE p.user_type = 'PHARMACIST' AND p.id=e.pharmacist_id \n" +
            "\tAND (e.status = 0 OR e.status = 1) AND e.patient_id=:patientId", nativeQuery = true)
    List<Pharmacist> getByPatientsId(@Param("patientId") long patientId);

    @Query(value = "SELECT case when (count(p.id) > 0) then true else false end\n" +
            "\tFROM pharmacy_user AS p, exam AS e WHERE p.user_type = 'PHARMACIST' AND p.id=e.pharmacist_id \n" +
            "\tAND (e.status = 0 OR e.status = 1) AND e.patient_id=:patientId AND p.id=:pharmacistId", nativeQuery = true)
    boolean canPatientRatePharmacist(long patientId, long pharmacistId);
}
