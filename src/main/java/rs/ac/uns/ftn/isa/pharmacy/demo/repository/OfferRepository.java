package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Offer;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    @Query(value = "SELECT id, price, shipping_days, status, order_id, supplier_id FROM order_offer WHERE supplier_id=:id", nativeQuery = true)
    Iterable<Offer> findBySupplierId(@Param("id") Long id);

    @Query(value = "SELECT medicine_amount FROM public.supplier_medicine_mapping" +
            " WHERE supplier_id=:supplierId AND medicine_id=:medicineId", nativeQuery = true)
    Integer findMedicineAmountBySupplierId(@Param("supplierId") Long supplierId, @Param("medicineId") Long medicineId);

    @Query(value = "SELECT medicine_id FROM public.supplier_medicine_mapping WHERE supplier_id=:supplierId", nativeQuery = true)
    List<Long> findAllMedicineIdsBySupplierId(@Param("supplierId") Long supplierId);

    @Query(value = "SELECT id, price, shipping_days, status, order_id, supplier_id FROM order_offer WHERE order_id=:orderId", nativeQuery = true)
    List<Offer> findOffersByOrder(@Param("orderId") Long orderID);

    @Query(value = "SELECT supplier_id FROM order_offer WHERE order_id=:orderId", nativeQuery = true)
    List<Long> getSupplierIds(@Param("orderId") Long orderId);

    @Query(value = "SELECT supplier_id FROM order_offer WHERE id=:offerId", nativeQuery = true)
    Long getAcceptedOfferSupplierId(@Param("offerId") Long offerId);
}
