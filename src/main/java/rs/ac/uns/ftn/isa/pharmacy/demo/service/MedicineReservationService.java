package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineReservation;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.CreateMedicineReservationParamsDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmaciesMedicinePriceDto;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Calendar;

public interface MedicineReservationService {
    Iterable<Medicine> getAllMedicine();

    Iterable<Pharmacy> getPharmaciesWithMedicineOnStock(Long medicineId);

    boolean isReservationValid(CreateMedicineReservationParamsDto createMedicineReservationParamsDto);

    void confirmReservation(CreateMedicineReservationParamsDto createMedicineReservationParamsDto, Patient patient) throws MessagingException;

    Medicine getMedicineById(Long medicineId);

    Iterable<MedicineReservation> getAllMedicineReservationsForPatient(Patient patient);

    boolean isMedicineReservationCancellable(Calendar deadline);

    void cancelMedicineReservation(Long medicineReservationId);

    void removeAllExpiredMedicineReservations();

    ArrayList<PharmaciesMedicinePriceDto> getPharmaciesMedicinePriceDtos(Long medicineId);
}
