package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;

import java.util.List;

public interface DermatologistRepository extends CrudRepository<Dermatologist, Long> {

    @Query(value = "SELECT id, email, password, enabled, last_password_reset_date, user_type, name, surname, rating FROM pharmacy_user WHERE user_type = 'DERMATOLOGIST'", nativeQuery = true)
    List<Dermatologist> getAllDermatologists();

    @Query(value = "SELECT DISTINCT pharmacy_user.id, email, password, pharmacy.id, enabled, last_password_reset_date, user_type, pharmacy_user.name, surname, pharmacy_user.rating FROM pharmacy_user JOIN dermatologist_employment_mapping ON pharmacy_user.id = dermatologist_employment_mapping.dermatologist_id JOIN pharmacy ON pharmacy.id = dermatologist_employment_mapping.pharmacy_id WHERE pharmacy.name = :pharmacyName and user_type = 'DERMATOLOGIST'", nativeQuery = true)
    List<Dermatologist> getDermatologistsByPharmacy(@Param("pharmacyName") String pharmacyName);

}
