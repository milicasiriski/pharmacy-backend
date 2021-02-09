package rs.ac.uns.ftn.isa.pharmacy.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {
    Medicine findByUuid(String uuid);

    @Query(value = "SELECT DISTINCT m.id, m.composition, m.description, m.form, m.manufacturer, m.name, m.points, m.prescribed,\n" +
            "\tm.ratings, m.recommended_dose, m.side_effects, m.type,m. uuid\n" +
            "\tFROM medicine AS m, purchase_medicine_mapping AS pm, medicine_purchase as p\n" +
            "\tWHERE m.id = pm.medicine_id AND pm.purchase_id = p.id\n" +
            "\tAND p.patient_id = :patientId", nativeQuery = true)
    Iterable<Medicine> findRateableByPatientId(long patientId);

    @Query(value = "SELECT case when (count(m.id) > 0) then true else false end \n" +
            "\tFROM medicine AS m, purchase_medicine_mapping AS pm, medicine_purchase as p\n" +
            "\tWHERE m.id = pm.medicine_id AND pm.purchase_id = p.id\n" +
            "\tAND p.patient_id = :patientId AND m.id = :medicineId", nativeQuery = true)
    boolean canPatientRateMedicine(long patientId, long medicineId);
}
