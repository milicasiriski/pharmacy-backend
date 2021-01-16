package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    @Query(value = "SELECT id, email, password, pharmacy_id, enabled, last_password_reset_date, user_type, name, surname FROM pharmacy_user WHERE user_type = 'DERMATOLOGIST'", nativeQuery=true)
    List<Dermatologist> getAllDermatologists();
}
