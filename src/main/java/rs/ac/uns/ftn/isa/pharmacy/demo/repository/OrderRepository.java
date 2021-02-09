package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

    @Query(value = "SELECT o.id, deadline, pharmacy_admin_id, offer_accepted FROM medicine_order AS o, pharmacy_user AS pu WHERE o.pharmacy_admin_id = pu.id and pu.pharmacy_id = :pharmacyId ", nativeQuery = true)
    List<Order> getOrdersByPharmacy(@Param("pharmacyId") Long pharmacyId);

    @Query(value = "SELECT o.id FROM medicine_order AS o, pharmacy_user AS pu WHERE o.pharmacy_admin_id = pu.id and pu.pharmacy_id = :pharmacyId ", nativeQuery = true)
    List<Long> getOrderIdsByPharmacy(@Param("pharmacyId") Long pharmacyId);
}
