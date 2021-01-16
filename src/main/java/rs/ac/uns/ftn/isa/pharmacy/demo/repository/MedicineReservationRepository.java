package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineReservation;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;

import java.util.List;

public interface MedicineReservationRepository extends CrudRepository<MedicineReservation, Long> {

    List<MedicineReservation> findByPatient(Patient patient);
}
