package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;

public interface MedicineReservationService {
    Iterable<Medicine> getAllMedicine();

    Iterable<Pharmacy> getPharmaciesWithMedicineOnStock(Long medicineId);

    boolean isReservationValid(MedicineReservationDto medicineReservationDto);

    void confirmReservation(MedicineReservationDto medicineReservationDto);
}
