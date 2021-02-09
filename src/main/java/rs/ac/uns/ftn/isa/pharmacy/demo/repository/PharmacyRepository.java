package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

import java.util.List;

public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {

    @Query(value = "SELECT p.id, about, city, country, latitude, longitude, street, name, rating, pharmacist_exam_duration, pharmacist_exam_price " +
            "FROM pharmacy as p, medicine_status_mapping as msm, medicine_status as ms " +
            "WHERE p.id = msm.pharmacy_id AND ms.id = msm.medicine_status_id " +
            "AND stock > 0 AND msm.medicine_id = :medicineId", nativeQuery = true)
    Iterable<Pharmacy> findWithMedicineOnStock(@Param("medicineId") Long medicineId);

    @Query(value = "SELECT p.id, about, city, country, latitude, longitude, street, p.name, p.rating, p.pharmacist_exam_duration, pharmacist_exam_price " +
            "FROM pharmacy AS p JOIN pharmacy_user AS u ON p.id = pharmacy_id " +
            "WHERE u.id = :pharmacyAdminId AND user_type = 'PHARMACY_ADMIN'", nativeQuery = true)
    Pharmacy findPharmacyByPharmacyAdmin(@Param("pharmacyAdminId") Long pharmacyAdminId);

    @Query(value = "SELECT id, about, city, country, latitude, longitude, street, name, rating, pharmacist_exam_duration, pharmacist_exam_price " +
            "FROM pharmacy WHERE rating >= :rating", nativeQuery = true)
    Iterable<Pharmacy> findWithRatingGreaterThan(@Param("rating") int rating);

    @Query(value = "SELECT DISTINCT p.id, p.about, p.city, p.country, p.latitude, p.longitude, p.street," +
            " p.name, p.pharmacist_exam_duration, p.pharmacist_exam_price, p.rating " +
            "FROM pharmacy AS p JOIN medicine_purchase AS mp ON p.id = pharmacy_id " +
            "where mp.patient_id = :patientId", nativeQuery = true)
    List<Pharmacy> findPharmacyByPatientIdPurchase(@Param("patientId") Long patientId);

    @Query(value = "SELECT DISTINCT p.id, p.about, p.city, p.country, p.latitude, p.longitude, p.street," +
            " p.name, p.pharmacist_exam_duration, p.pharmacist_exam_price, p.rating " +
            "FROM pharmacy AS p, exam AS e, dermatologist_employment_mapping AS dem " +
            "where p.id=dem.pharmacy_id AND e.patient_id=:patientId"
            , nativeQuery = true)
    List<Pharmacy> findPharmacyByPatientIdDermatologistsExam(@Param("patientId") Long patientId);

    @Query(value = "SELECT DISTINCT p.id, p.about, p.city, p.country, p.latitude, p.longitude, p.street," +
            " p.name, p.pharmacist_exam_duration, p.pharmacist_exam_price, p.rating " +
            "FROM pharmacy AS p, exam AS e, pharmacy_user AS u " +
            "where u.id=e.pharmacist_id AND p.id=u.pharmacy_id AND e.patient_id=:patientId", nativeQuery = true)
    List<Pharmacy> findPharmacyByPatientIdPhysiciansExam(@Param("patientId") Long patientId);
}
