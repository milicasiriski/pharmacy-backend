package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;

import java.util.List;

@RestController
@RequestMapping(value = "/medicine/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineReservationController {
    private MedicineReservationService medicineReservationService;

    @Autowired
    public MedicineReservationController(MedicineReservationService medicineReservationService) {
        this.medicineReservationService = medicineReservationService;
    }

    @GetMapping("/medicine")
    public List<Medicine> getAllMedicine() {
        return medicineReservationService.getAllMedicine();
    }

    @GetMapping("/pharmacies")
    public List<Pharmacy> getPharmaciesWithMedicineOnStock(String medicineId) {
        return medicineReservationService.getPharmaciesWithMedicineOnStock(medicineId);
    }

    @PostMapping("")
    public void confirmReservation(MedicineReservationDto medicineReservationDto) {
        if (medicineReservationService.isReservationValid(medicineReservationDto)) {
            medicineReservationService.confirmReservation(medicineReservationDto);
        }
    }
}
