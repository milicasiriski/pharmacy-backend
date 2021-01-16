package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;

import java.util.List;

public interface MedicineReservationService {
    List<Medicine> getAllMedicine();

    List<Pharmacy> getPharmaciesWithMedicineOnStock(String medicineId);

    boolean isReservationValid(MedicineReservationDto medicineReservationDto);

    void confirmReservation(MedicineReservationDto medicineReservationDto);
}
