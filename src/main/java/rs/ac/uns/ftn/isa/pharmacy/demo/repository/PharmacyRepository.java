package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

import java.util.List;

public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {

    @Query(value = "SELECT id, about, address, name FROM pharmacy JOIN pharmacy_medicine_stock_mapping " +
            "ON id = pharmacy_id WHERE stock > 0 AND medicine_id = :medicineId", nativeQuery = true)
    Iterable<Pharmacy> findWithMedicineOnStock(@Param("medicineId") Long medicineId);

    List<Pharmacy> findAll();
}
