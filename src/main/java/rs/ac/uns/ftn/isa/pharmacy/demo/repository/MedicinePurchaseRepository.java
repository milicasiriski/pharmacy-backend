package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicinePurchase;

public interface MedicinePurchaseRepository extends CrudRepository<MedicinePurchase, Long> {
}
