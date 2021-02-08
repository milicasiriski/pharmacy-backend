package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicinePurchase;

import java.util.Calendar;

public interface MedicinePurchaseRepository extends CrudRepository<MedicinePurchase, Long> {

    @Query(value = "SELECT SUM(purchase_medicine_mapping.medicine_amount) FROM medicine_purchase, purchase_medicine_mapping WHERE\n" +
            "\t\t\tmedicine_purchase.id = purchase_medicine_mapping.purchase_id AND\n" +
            "purchase_date BETWEEN :dateStart AND :dateEnd AND pharmacy_id = :pharmacyId AND medicine_id = :medicineId", nativeQuery = true)
    Integer getMedicinePurchaseByPharmacyAndDate(@Param("dateStart") Calendar dateStart, @Param("dateEnd") Calendar dateEnd,
                                                 @Param("pharmacyId") Long pharmacyId, @Param("medicineId") Long medicineId);
}
