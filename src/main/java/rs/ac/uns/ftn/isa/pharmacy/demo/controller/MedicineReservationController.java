package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.MedicineReservationCannotBeCancelledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.CreateMedicineReservationParamsDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.GetMedicineReservationResponse;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmaciesMedicinePriceDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/medicine-reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineReservationController {

    private final MedicineReservationService medicineReservationService;

    @Autowired
    public MedicineReservationController(MedicineReservationService medicineReservationService) {
        this.medicineReservationService = medicineReservationService;
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/")
    public ResponseEntity<Iterable<GetMedicineReservationResponse>> getAllMedicineReservations() {
        Patient patient = getSignedInUser();

        List<GetMedicineReservationResponse> medicineReservations = new ArrayList<>();
        medicineReservationService.getAllMedicineReservationsForPatient(patient).forEach(medicineReservation -> {
            boolean cancellable = medicineReservationService.isMedicineReservationCancellable(medicineReservation.getExpirationDate());
            medicineReservations.add(new GetMedicineReservationResponse(medicineReservation, cancellable));
        });
        return new ResponseEntity<>(medicineReservations, HttpStatus.OK);
    }

    @GetMapping("/pharmacies/{medicineId}")
    public ResponseEntity<Iterable<PharmaciesMedicinePriceDto>> getPharmaciesWithMedicineOnStock(@PathVariable("medicineId") Long medicineId) {
        ArrayList<PharmaciesMedicinePriceDto> result = medicineReservationService.getPharmaciesMedicinePriceDtos(medicineId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/")
    public ResponseEntity<Void> confirmReservation(@RequestBody CreateMedicineReservationParamsDto createMedicineReservationParamsDto) {
        if (medicineReservationService.isReservationValid(createMedicineReservationParamsDto)) {
            try {
                medicineReservationService.confirmReservation(createMedicineReservationParamsDto, getSignedInUser());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (MessagingException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @DeleteMapping("/cancel/{medicineReservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("medicineReservationId") Long medicineReservationId) {
        try {
            medicineReservationService.cancelMedicineReservation(medicineReservationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Medicine reservation does not exist!", HttpStatus.BAD_REQUEST);
        } catch (MedicineReservationCannotBeCancelledException e) {
            return new ResponseEntity<>("Selected medicine reservation cannot be deleted!", HttpStatus.BAD_REQUEST);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("The reservation could not be cancelled, please try again.", HttpStatus.I_AM_A_TEAPOT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Scheduled(cron = "${medicineReservationCleanUp.cron}")
    public void executeMedicineReservationCleanUp() {
        medicineReservationService.removeAllExpiredMedicineReservations();
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
