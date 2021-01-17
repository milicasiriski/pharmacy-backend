package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {

    Medicine findByName(String name);
}
