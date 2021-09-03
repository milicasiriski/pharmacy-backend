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
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineReservation;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PenaltyPointsConstants;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/medicine-reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineReservationController {

    private final MedicineReservationService medicineReservationService;
    private final PatientService patientService;

    @Autowired
    public MedicineReservationController(MedicineReservationService medicineReservationService, PatientService patientService) {
        this.medicineReservationService = medicineReservationService;
        this.patientService = patientService;
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

    @PreAuthorize("hasRole('ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<GetMedicineReservationResponse>> getAllMedicineReservationsForPharmacist() {
        Pharmacist pharmacist = getSignedInUserPharmacist();

        List<GetMedicineReservationResponse> medicineReservations = new ArrayList<>();
        medicineReservationService.getAllMedicineReservations().forEach(medicineReservation -> {
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

    @GetMapping("/getMedicineByID/{medicineId}")
    public ResponseEntity<Iterable<GetMedicineReservationResponse>> getMedicineByID(@PathVariable("medicineId") Long medicineId) {
        MedicineReservation result = medicineReservationService.getMedicineReservationById(medicineId);
        ArrayList<GetMedicineReservationResponse> returnResult = new ArrayList<>();
        returnResult.add(new GetMedicineReservationResponse(result, true));
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    @GetMapping("/getMedicineByUniqueNumber/{uniqueNumber}")
    public ResponseEntity<Iterable<MedicineReservationDTO>> getMedicineByUniqueNumber(@PathVariable("uniqueNumber") String medicineUniqueNumber) {
        MedicineReservation result = medicineReservationService.getMedicineReservationByUniqueNumber(medicineUniqueNumber);
        ArrayList<MedicineReservationDTO> returnResult = new ArrayList<>();
        returnResult.add(new MedicineReservationDTO(result, true));
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/")
    public ResponseEntity<String> confirmReservation(@RequestBody CreateMedicineReservationParamsDto createMedicineReservationParamsDto) {
        try {
            if (patientService.hasCurrentUserExceededPenaltyPoints()) {
                return new ResponseEntity<>(PenaltyPointsConstants.PENALTY_POINTS_EXCEEDED_MESSAGE, HttpStatus.I_AM_A_TEAPOT);
            }
            if (!medicineReservationService.isReservationValid(createMedicineReservationParamsDto)) {
                return new ResponseEntity<>("The reservation id not valid, please try again.", HttpStatus.BAD_REQUEST);
            }
            medicineReservationService.confirmReservation(createMedicineReservationParamsDto, getSignedInUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("The reservation could not be made, please try again.", HttpStatus.I_AM_A_TEAPOT);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Email could not be sent!", HttpStatus.I_AM_A_TEAPOT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PHARMACIST', 'ROLE_DERMATOLOGIST')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/exam")
    public ResponseEntity<String> confirmReservationExam(@RequestBody ExamMedicineDTO examMedicineDTO) {
        try {
            if (patientService.hasPatientExceededPenaltyPoints(examMedicineDTO.getPatientId())) {
                return new ResponseEntity<>(PenaltyPointsConstants.PENALTY_POINTS_EXCEEDED_MESSAGE, HttpStatus.I_AM_A_TEAPOT);
            }
            if (!medicineReservationService.isReservationValid(new CreateMedicineReservationParamsDto(examMedicineDTO.getMedicineId(), examMedicineDTO.getPharmacyId(), examMedicineDTO.getExpirationDate()))) {
                return new ResponseEntity<>("The reservation id not valid, please try again.", HttpStatus.BAD_REQUEST);
            }
            Patient patient = patientService.getPatientByEmail(examMedicineDTO.getPatientId());
            medicineReservationService.confirmReservation(new CreateMedicineReservationParamsDto(examMedicineDTO.getMedicineId(), examMedicineDTO.getPharmacyId(), examMedicineDTO.getExpirationDate()), patient);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("The reservation could not be made, please try again.", HttpStatus.I_AM_A_TEAPOT);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Email could not be sent!", HttpStatus.I_AM_A_TEAPOT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
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

    @PreAuthorize("hasRole('ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @DeleteMapping("/issue/{medicineReservationId}")
    public ResponseEntity<String> issueReservation(@PathVariable("medicineReservationId") Long medicineReservationId) {
        try {
            medicineReservationService.issueMedicineReservation(medicineReservationId);
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

    private Pharmacist getSignedInUserPharmacist() {
        return (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
