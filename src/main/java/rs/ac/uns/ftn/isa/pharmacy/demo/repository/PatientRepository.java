package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query(value = "SELECT DISTINCT u.id, u.version, u.user_type, u.email, u.enabled, u.last_password_reset_date," +
            " u.name, u.password, u.surname, u.patient_activation_code," +
            " u.street, u.city, u.country,u.longitude,u.latitude, u.patient_loyalty_points,u.patient_penalty_points," +
            " u.patient_phone_num  FROM pharmacy_subscribers AS ps," +
            " pharmacy_user AS u WHERE ps.patient_id=u.id AND ps.pharmacy_id=:id", nativeQuery = true)
    List<Patient> findSubscribersByPharmacyId(@Param("id") Long id);

    @Query(value = "SELECT patient_id FROM pharmacy_subscribers WHERE pharmacy_id=:id", nativeQuery = true)
    List<Long> findSubscribersIdsByPharmacyId(@Param("id") Long id);
    //Patient findPatientByEmail(String email);
    Patient findByEmail(String email);
    Patient getById(long patientID);
}
