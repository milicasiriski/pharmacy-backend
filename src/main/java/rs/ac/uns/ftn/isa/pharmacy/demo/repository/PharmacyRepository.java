package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;

public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {
}
