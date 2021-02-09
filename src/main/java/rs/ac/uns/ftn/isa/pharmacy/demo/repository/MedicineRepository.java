package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {

    Medicine findByName(String name);

    Medicine findByUuid(String uuid);

    // TODO: get medicine from reservations as well
    @Query(value = "SELECT DISTINCT m.id, m.composition, m.description, m.form, m.manufacturer, m.name, m.points, " +
            "m.prescribed, m.ratings, m.recommended_dose, m.side_effects, m.type,m. uuid\n" +
            "\tFROM medicine AS m, prescription_medicine_mapping as mp, prescription as p\n" +
            "\tWHERE m.id = mp.medicine_id AND p.id = mp.prescription_id AND p.patient_id = :patientId", nativeQuery = true)
    Iterable<Medicine> findRateableByPatientId(long patientId);
}
