package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineSearch;

import java.util.List;

public interface SearchedMedicineRepository extends CrudRepository<MedicineSearch, Long> {

    @Query(value = "SELECT id, medicine_id, search_date, pharmacy_id FROM medicine_search WHERE pharmacy_id = :pharmacyId", nativeQuery = true)
    List<MedicineSearch> findAllByPharmacyId(@Param("pharmacyId") Long pharmacyId);
}
