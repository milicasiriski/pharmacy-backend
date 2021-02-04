package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamAlreadyScheduledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.GetPharmaciesForPharmacistExamResponse;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.GetPharmacistsForPharmacistExamResponse;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SchedulePharmacistExamParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistExamSchedulingService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacistSortType;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacySortType;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/pharmacistExam", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientPharmacistExamController {
    private final PharmacistExamSchedulingService pharmacistExamSchedulingService;

    public PatientPharmacistExamController(PharmacistExamSchedulingService pharmacistExamSchedulingService) {
        this.pharmacistExamSchedulingService = pharmacistExamSchedulingService;
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/")
    public ResponseEntity<String> scheduleAppointment(@RequestBody SchedulePharmacistExamParams params) {
        try {
            pharmacistExamSchedulingService.scheduleAppointment(params, getSignedInUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Selected pharmacist does not exist!", HttpStatus.BAD_REQUEST);
        } catch (ExamAlreadyScheduledException e) {
            return new ResponseEntity<>("Appointment is no longer available, please try again!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Oops! Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacies/{dateTime}")
    public ResponseEntity<Iterable<GetPharmaciesForPharmacistExamResponse>> getPharmaciesWithAvailablePharmacists(@PathVariable("dateTime") Date dateTime) {
        try {
            Iterable<GetPharmaciesForPharmacistExamResponse> response = getPharmacies(dateTime, PharmacySortType.NONE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacies/{dateTime}/{sort}")
    public ResponseEntity<Iterable<GetPharmaciesForPharmacistExamResponse>> getPharmaciesWithAvailablePharmacists(@PathVariable("dateTime") Date dateTime, @PathVariable("sort") PharmacySortType sort) {
        try {
            Iterable<GetPharmaciesForPharmacistExamResponse> response = getPharmacies(dateTime, sort);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacists/{pharmacyId}/{dateTime}")
    public ResponseEntity<Iterable<GetPharmacistsForPharmacistExamResponse>> getAvailablePharmacists(@PathVariable Date dateTime, @PathVariable long pharmacyId) {
        try {
            Iterable<GetPharmacistsForPharmacistExamResponse> response = getPharmacists(dateTime, pharmacyId, PharmacistSortType.NONE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/pharmacists/{pharmacyId}/{dateTime}/{sort}")
    public ResponseEntity<Iterable<GetPharmacistsForPharmacistExamResponse>> getAvailablePharmacists(@PathVariable Date dateTime, @PathVariable long pharmacyId, @PathVariable PharmacistSortType sort) {
        try {
            Iterable<GetPharmacistsForPharmacistExamResponse> response = getPharmacists(dateTime, pharmacyId, sort);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Iterable<GetPharmaciesForPharmacistExamResponse> getPharmacies(Date dateTime, PharmacySortType sort) {
        List<GetPharmaciesForPharmacistExamResponse> result = new ArrayList<>();
        pharmacistExamSchedulingService.getPharmaciesWithAvailableAppointments(dateTime, sort).forEach(pharmacy ->
                result.add(new GetPharmaciesForPharmacistExamResponse(pharmacy))
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
}
