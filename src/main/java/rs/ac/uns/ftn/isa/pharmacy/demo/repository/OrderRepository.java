package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
