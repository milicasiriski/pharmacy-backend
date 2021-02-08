package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Complaint;

public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
    Iterable<Complaint> findAllByResolved(boolean resolved);
}
