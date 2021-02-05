package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;

import java.util.List;

public interface PharmacistRepository extends CrudRepository<Pharmacist, Long> {

    @Query(value = "SELECT id, email, password, enabled, last_password_reset_date, user_type, name, surname, pharmacy_id FROM pharmacy_user WHERE user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getAll();

    @Query(value = "SELECT DISTINCT pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname, pharmacy_id, pharmacy_user.rating FROM pharmacy_user JOIN pharmacy ON pharmacy.id = pharmacy_id WHERE pharmacy.name = :pharmacyName and user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getByPharmacyName(@Param("pharmacyName") String pharmacyName);

    @Query(value = "SELECT DISTINCT id, email, password, enabled, last_password_reset_date, user_type, name, surname, pharmacy_id, rating " +
            "\tFROM pharmacy_user WHERE pharmacy_id = :pharmacyId and user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> findByPharmacyId(@Param("pharmacyId") long pharmacyId);

    @Query(value = "SELECT city, country, latitude, longitude, street, id, email, password, enabled, last_password_reset_date, user_type, name, surname FROM pharmacy_user WHERE user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getAllPharmacists();

    @Query(value = "SELECT DISTINCT pharmacy_user.city, pharmacy_user.country, pharmacy_user.latitude, pharmacy_user.longitude, pharmacy_user.street, pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname FROM pharmacy_user JOIN pharmacy_pharmacists ON pharmacy_user.id = pharmacy_pharmacists.pharmacist_id JOIN pharmacy ON pharmacy.id = pharmacy_pharmacists.pharmacy_id WHERE pharmacy.name = :pharmacyName and user_type = 'PHARMACIST'", nativeQuery = true)
    List<Pharmacist> getPharmacistByPharmacy(@Param("pharmacyName") String pharmacyName);

    Pharmacist getPharmacistByNameAndSurname(String name, String surname);
}
