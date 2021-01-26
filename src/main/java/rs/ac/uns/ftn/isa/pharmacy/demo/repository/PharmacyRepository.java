package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

import java.util.List;

public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {

    @Query(value = "SELECT p.id, about, address, name, rating " +
            "FROM pharmacy as p, medicine_status_mapping as msm, medicine_status as ms " +
            "WHERE p.id = msm.pharmacy_id AND ms.id = msm.medicine_status_id " +
            "AND stock > 0 AND msm.medicine_id = :medicineId", nativeQuery = true)
    Iterable<Pharmacy> findWithMedicineOnStock(@Param("medicineId") Long medicineId);

    List<Pharmacy> findAll();

    @Query(value = "SELECT p.id, about, address, p.name, rating FROM pharmacy AS p JOIN pharmacy_user AS u ON p.id = pharmacy_id where u.id = :pharmacyAdminId AND user_type = 'PHARMACY_ADMIN'", nativeQuery = true)
    Pharmacy findPharmacyByPharmacyAdmin(@Param("pharmacyAdminId") Long pharmacyAdminId);
}
