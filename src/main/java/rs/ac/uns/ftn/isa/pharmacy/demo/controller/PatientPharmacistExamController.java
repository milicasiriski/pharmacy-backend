package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistExamSchedulingService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PenaltyPointsConstants;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacistSortType;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacySortType;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/pharmacistExam", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientPharmacistExamController {
    private final PharmacistExamSchedulingService pharmacistExamSchedulingService;
    private final PatientService patientService;

    @Autowired
    public PatientPharmacistExamController(PharmacistExamSchedulingService pharmacistExamSchedulingService, PatientService patientService) {
        this.pharmacistExamSchedulingService = pharmacistExamSchedulingService;
        this.patientService = patientService;
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/")
    public ResponseEntity<Iterable<ExamDetails>> getPharmacistExamsForPatient() {
        Iterable<ExamDetails> exams = pharmacistExamSchedulingService.getScheduledPharmacistExamsForPatient(getSignedInUser());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/all")
    public ResponseEntity<Iterable<GetPharmacistExamResponse>> getAllPharmacistExams() {
        Iterable<GetPharmacistExamResponse> exams = pharmacistExamSchedulingService.getAllScheduledPharmacistExamsForPatient(getSignedInPharmacist());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/examHistory")
    public ResponseEntity<Iterable<ExamDetails>> getPharmacistExamHistoryForPatient() {
        Iterable<ExamDetails> exams = pharmacistExamSchedulingService.getPharmacistExamHistoryForPatient(getSignedInUser());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/")
    public ResponseEntity<String> scheduleAppointment(@RequestBody SchedulePharmacistExamParams params) {
        try {
            if (patientService.hasCurrentUserExceededPenaltyPoints()) {
                return new ResponseEntity<>(PenaltyPointsConstants.PENALTY_POINTS_EXCEEDED_MESSAGE, HttpStatus.I_AM_A_TEAPOT);
            }
            pharmacistExamSchedulingService.scheduleAppointment(params, getSignedInUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("The reservation could not be cancelled, please try again.", HttpStatus.I_AM_A_TEAPOT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Selected pharmacist does not exist!", HttpStatus.BAD_REQUEST);
        } catch (ExamAlreadyScheduledException e) {
            return new ResponseEntity<>("Appointment is no longer available, please try again!", HttpStatus.BAD_REQUEST);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Email could not be sent.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Oops! Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @PutMapping("/scheduleForPatientPharmacist")
    public ResponseEntity<String> schedulePharmacistExamForPatient(@RequestBody ExamAndPatiendIDDTO examAndPatiendIDDTO) {
        try {
            //if (patientService.hasCurrentUserExceededPenaltyPoints()) {
            //    return new ResponseEntity<>(PenaltyPointsConstants.PENALTY_POINTS_EXCEEDED_MESSAGE, HttpStatus.I_AM_A_TEAPOT);
            //}
            long id = Long.parseLong(examAndPatiendIDDTO.getExamID());
            pharmacistExamSchedulingService.scheduleAppointmentForPharmacist(id, examAndPatiendIDDTO.getPatientID(), getSignedInPharmacist());
            return new ResponseEntity<>("Exam successfully scheduled!", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The exam you've tried to schedule does not exist.", HttpStatus.BAD_REQUEST);
        } catch (ExamAlreadyScheduledException e) {
            return new ResponseEntity<>("Sorry, the exam is no longer available.", HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Wrong id format.", HttpStatus.BAD_REQUEST);
        } catch (MessagingException e) {
            return new ResponseEntity<>("The confirmation email cannot be sent, please try again!", HttpStatus.BAD_REQUEST);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("Looks like this exam has already been scheduled!", HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/createAndScheduleForPatientPharmacist")
    @PreAuthorize("hasRole('ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> createAndScheduleExamPharmacist(@RequestBody DermatologistExamDTO dermatologistExamDTO) {
        try {
            dermatologistExamDTO.setDermatologistId(getSignedInPharmacist().getId());

            //examService.createAndScheduleExamForDermatologist(dermatologistExamDTO);
            return new ResponseEntity<>("Exam successfully added!", HttpStatus.OK);
        } catch (ExamIntervalIsOverlapping e) {
            return new ResponseEntity<>("Dermatologists is already scheduled for chosen date!", HttpStatus.BAD_REQUEST);
        } catch (ExamIntervalIsNotInShiftIntervalException e) {
            return new ResponseEntity<>("Dermatologists is not available at that time!", HttpStatus.BAD_REQUEST);
        } catch (ShiftIsNotDefinedException e) {
            return new ResponseEntity<>("Shift is not defined for that day!", HttpStatus.BAD_REQUEST);
        } catch (OptimisticLockException e) {
            return new ResponseEntity<>("Looks like this time interval is already scheduled!", HttpStatus.I_AM_A_TEAPOT);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Please input all fields!", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacies/{dateTime}")
    public ResponseEntity<Iterable<PharmacyDto>> getPharmaciesWithAvailablePharmacists(@PathVariable("dateTime") Date dateTime) {
        try {
            Iterable<PharmacyDto> response = getPharmacies(dateTime, PharmacySortType.NONE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacies/{dateTime}/{sort}")
    public ResponseEntity<Iterable<PharmacyDto>> getPharmaciesWithAvailablePharmacists(@PathVariable("dateTime") Date dateTime, @PathVariable("sort") PharmacySortType sort) {
        try {
            Iterable<PharmacyDto> response = getPharmacies(dateTime, sort);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacists/{pharmacyId}/{dateTime}")
    public ResponseEntity<Iterable<GetPharmacistsForPharmacistExamResponse>> getAvailablePharmacists(@PathVariable Date dateTime, @PathVariable long pharmacyId) {
        try {
            Iterable<GetPharmacistsForPharmacistExamResponse> response = getPharmacists(dateTime, pharmacyId, PharmacistSortType.NONE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacists/{pharmacyId}/{dateTime}/{sort}")
    public ResponseEntity<Iterable<GetPharmacistsForPharmacistExamResponse>> getAvailablePharmacists(@PathVariable Date dateTime, @PathVariable long pharmacyId, @PathVariable PharmacistSortType sort) {
        try {
            Iterable<GetPharmacistsForPharmacistExamResponse> response = getPharmacists(dateTime, pharmacyId, sort);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')") // NOSONAR the focus of this project is not on web security
    @DeleteMapping("/cancel/{examId}")
    public ResponseEntity<String> cancelDermatologistExam(@PathVariable long examId) {
        try {
            pharmacistExamSchedulingService.cancelAppointment(examId, getSignedInUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (WrongPatientException e) {
            return new ResponseEntity<>("You are not authorized to cancel this exam.", HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The exam you've tried to cancel does not exist.", HttpStatus.BAD_REQUEST);
        } catch (ExamCanNoLongerBeCancelledException e) {
            return new ResponseEntity<>("Sorry, the exam can no longer be cancelled.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Oops! Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    private Iterable<PharmacyDto> getPharmacies(Date dateTime, PharmacySortType sort) {
        List<PharmacyDto> result = new ArrayList<>();
        pharmacistExamSchedulingService.getPharmaciesWithAvailableAppointments(dateTime, sort).forEach(pharmacy ->
                result.add(new PharmacyDto(pharmacy))
        );
        return result;
    }

    private Iterable<GetPharmacistsForPharmacistExamResponse> getPharmacists(Date dateTime, long pharmacyId, PharmacistSortType sort) {
        List<GetPharmacistsForPharmacistExamResponse> result = new ArrayList<>();
        pharmacistExamSchedulingService.getPharmacistsWithAvailableAppointments(dateTime, pharmacyId, sort).forEach(pharmacist ->
                result.add(new GetPharmacistsForPharmacistExamResponse(pharmacist))
        );
        return result;
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Pharmacist getSignedInPharmacist() {
        return (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
