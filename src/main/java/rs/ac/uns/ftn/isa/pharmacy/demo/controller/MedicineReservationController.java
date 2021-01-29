package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmaciesMedicinePriceDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;

import javax.mail.MessagingException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/medicine-reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineReservationController {
    private final MedicineReservationService medicineReservationService;

    @Autowired
    public MedicineReservationController(MedicineReservationService medicineReservationService) {
        this.medicineReservationService = medicineReservationService;
    }

    @GetMapping("/medicine")
    public ResponseEntity<Iterable<Medicine>> getAllMedicine() {
        return new ResponseEntity<>(medicineReservationService.getAllMedicine(), HttpStatus.OK);
    }

    @GetMapping("/pharmacies/{medicineId}")
    public ResponseEntity<Iterable<PharmaciesMedicinePriceDto>> getPharmaciesWithMedicineOnStock(@PathVariable("medicineId") Long medicineId) {
        ArrayList<PharmaciesMedicinePriceDto> result = new ArrayList<>();
        Medicine medicine = medicineReservationService.getMedicineById(medicineId);

        medicineReservationService.getPharmaciesWithMedicineOnStock(medicineId).forEach(pharmacy -> {
            result.add(new PharmaciesMedicinePriceDto(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress(), pharmacy.getAbout(), pharmacy.getCurrentMedicinePrice(medicine)));
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/")
    public ResponseEntity<Void> confirmReservation(@RequestBody MedicineReservationDto medicineReservationDto) {
        if (medicineReservationService.isReservationValid(medicineReservationDto)) {
            try {
                medicineReservationService.confirmReservation(medicineReservationDto, getSignedInUser());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (MessagingException e) {
                System.out.println("MAIL SERVICE FAILED");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
