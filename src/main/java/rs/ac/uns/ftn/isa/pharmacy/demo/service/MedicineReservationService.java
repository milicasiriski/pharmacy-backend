package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineReservation;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.CreateMedicineReservationParams;

import javax.mail.MessagingException;
import java.util.Calendar;

public interface MedicineReservationService {
    Iterable<Medicine> getAllMedicine();

    Iterable<Pharmacy> getPharmaciesWithMedicineOnStock(Long medicineId);

    boolean isReservationValid(CreateMedicineReservationParams createMedicineReservationParams);

    void confirmReservation(CreateMedicineReservationParams createMedicineReservationParams, Patient patient) throws MessagingException;

    Medicine getMedicineById(Long medicineId);

    Iterable<MedicineReservation> getAllMedicineReservationsForPatient(Patient patient);

    boolean isMedicineReservationCancellable(Calendar deadline);

    void cancelMedicineReservation(Long medicineReservationId);
}
