package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequest;

public interface VacationRepository extends CrudRepository<VacationTimeRequest, Long> {
}
