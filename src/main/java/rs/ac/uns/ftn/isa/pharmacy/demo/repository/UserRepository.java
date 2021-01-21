package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    @Query(value = "SELECT id, email, password, enabled, last_password_reset_date, user_type, name, surname FROM pharmacy_user WHERE user_type = 'DERMATOLOGIST'", nativeQuery=true)
    List<Dermatologist> getAllDermatologists();

    @Query(value = "SELECT DISTINCT pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname FROM pharmacy_user JOIN pharmacy_dermatologist ON pharmacy_user.id = pharmacy_dermatologist.dermatologist_id JOIN pharmacy ON pharmacy.id = pharmacy_dermatologist.pharmacy_id WHERE pharmacy.name = :pharmacyName and user_type = 'DERMATOLOGIST'", nativeQuery=true)
    List<Dermatologist> getDermatologistsByPharmacy(@Param("pharmacyName") String pharmacyName);

    @Query(value = "SELECT id, email, password, enabled, last_password_reset_date, user_type, name, surname FROM pharmacy_user WHERE user_type = 'PHARMACIST'", nativeQuery=true)
    List<Pharmacist> getAllPharmacists();

    @Query(value = "SELECT DISTINCT pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname FROM pharmacy_user JOIN pharmacy_pharmacist ON pharmacy_user.id = pharmacy_pharmacist.pharmacist_id JOIN pharmacy ON pharmacy.id = pharmacy_pharmacist.pharmacy_id WHERE pharmacy.name = :pharmacyName and user_type = 'PHARMACIST'", nativeQuery=true)
    List<Pharmacist> getPharmacistByPharmacy(@Param("pharmacyName") String pharmacyName);
}
