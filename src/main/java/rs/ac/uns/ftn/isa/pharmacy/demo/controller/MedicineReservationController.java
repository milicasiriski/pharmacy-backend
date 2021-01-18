package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    @GetMapping("/pharmacies/{medicineId}")
    public ResponseEntity<Iterable<PharmacyBasicInfoDto>> getPharmaciesWithMedicineOnStock(@PathVariable("medicineId") Long medicineId) {
        ArrayList<PharmacyBasicInfoDto> result = new ArrayList<>();
        medicineReservationService.getPharmaciesWithMedicineOnStock(medicineId).forEach(pharmacy -> {
            result.add(new PharmacyBasicInfoDto(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress(), pharmacy.getAbout()));
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> confirmReservation(@RequestBody MedicineReservationDto medicineReservationDto) {
        if (medicineReservationService.isReservationValid(medicineReservationDto)) {
            try {
                medicineReservationService.confirmReservation(medicineReservationDto);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
