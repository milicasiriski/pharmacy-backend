package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;

import java.util.List;

@Service
public class MedicineReservationServiceImpl implements MedicineReservationService {
    private MedicineRepository medicineRepository;
    private MedicineReservationRepository medicineReservationRepository;
    private PharmacyRepository pharmacyRepository;

    @Autowired
    public MedicineReservationServiceImpl(MedicineRepository medicineRepository, MedicineReservationRepository medicineReservationRepository, PharmacyRepository pharmacyRepository) {
        this.medicineRepository = medicineRepository;
        this.medicineReservationRepository = medicineReservationRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<Medicine> getAllMedicine() {
        return null;
    }

    @Override
    public List<Pharmacy> getPharmaciesWithMedicineOnStock(String medicineId) {
        return null;
    }

    @Override
    public boolean isReservationValid(MedicineReservationDto medicineReservationDto) {
        return false;
    }

    @Override
    public void confirmReservation(MedicineReservationDto medicineReservationDto) {

    }
}
