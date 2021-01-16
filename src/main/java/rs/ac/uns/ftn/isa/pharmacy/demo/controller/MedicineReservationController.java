package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;

import java.util.List;

@RestController
@RequestMapping(value = "/medicine-reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineReservationController {
    private MedicineReservationService medicineReservationService;

    @Autowired
    public MedicineReservationController(MedicineReservationService medicineReservationService) {
        this.medicineReservationService = medicineReservationService;
    }

    @GetMapping("/medicine")
    public ResponseEntity<Iterable<Medicine>> getAllMedicine() {
        return new ResponseEntity<>(medicineReservationService.getAllMedicine(), HttpStatus.OK);
    }

    @GetMapping("/pharmacies")
    public ResponseEntity<Iterable<Pharmacy>> getPharmaciesWithMedicineOnStock(String medicineId) {
        return new ResponseEntity<>(medicineReservationService.getPharmaciesWithMedicineOnStock(medicineId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> confirmReservation(MedicineReservationDto medicineReservationDto) {
        if (medicineReservationService.isReservationValid(medicineReservationDto)) {
            medicineReservationService.confirmReservation(medicineReservationDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
