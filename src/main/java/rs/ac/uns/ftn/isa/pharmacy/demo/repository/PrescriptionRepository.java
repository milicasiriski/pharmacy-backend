package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, String> {
    Iterable<Prescription> findByPatient(Patient patient);
}
