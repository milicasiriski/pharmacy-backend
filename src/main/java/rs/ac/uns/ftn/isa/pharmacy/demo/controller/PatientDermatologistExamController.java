package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamAlreadyScheduledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamCanNoLongerBeCancelledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.WrongPatientException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.GetAvailableDermatologistExamsResponse;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ExamService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.ExamSortType;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/patient-exam", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientDermatologistExamController {
    private final ExamService examService;

    @Autowired
    public PatientDermatologistExamController(ExamService examService) {
        this.examService = examService;
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/")
    public ResponseEntity<Iterable<ExamDetails>> getDermatologistExamsForPatient() {
        Iterable<ExamDetails> exams = examService.getDermatologistExamsForPatient(getSignedInUser());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR
    @GetMapping("/{pharmacyId}")
    public ResponseEntity<Iterable<GetAvailableDermatologistExamsResponse>> getAvailableDermatologistExamsForPharmacy(@PathVariable("pharmacyId") String pharmacyId) {
        try {
            long id = Long.parseLong(pharmacyId);
            ArrayList<GetAvailableDermatologistExamsResponse> response = new ArrayList<>();
            examService.getAvailableDermatologistExamsForPharmacy(id, ExamSortType.NONE).forEach(it ->
                    response.add(new GetAvailableDermatologistExamsResponse(it.getExam(), it.getDermatologist())));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR
    @GetMapping("/{pharmacyId}/{sort}")
    public ResponseEntity<Iterable<GetAvailableDermatologistExamsResponse>> getSortedAvailableDermatologistExamsForPharmacy(@PathVariable("pharmacyId") long pharmacyId, @PathVariable("sort") ExamSortType sort) {
        ArrayList<GetAvailableDermatologistExamsResponse> response = new ArrayList<>();
        examService.getAvailableDermatologistExamsForPharmacy(pharmacyId, sort).forEach(it ->
                response.add(new GetAvailableDermatologistExamsResponse(it.getExam(), it.getDermatologist())));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @PutMapping("/")
    public ResponseEntity<String> scheduleDermatologistExam(@RequestBody String examId) {
        try {
            long id = Long.parseLong(examId);
            examService.scheduleDermatologistExam(id, getSignedInUser());
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

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @PutMapping("/cancel/")
    public ResponseEntity<String> cancelDermatologistExam(@RequestBody String examId) {
        try {
            long id = Long.parseLong(examId);
            examService.cancelDermatologistExam(id, getSignedInUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (WrongPatientException e) {
            return new ResponseEntity<>("You are not authorized to cancel this exam.", HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The exam you've tried to cancel does not exist.", HttpStatus.BAD_REQUEST);
        } catch (ExamCanNoLongerBeCancelledException e) {
            return new ResponseEntity<>("Sorry, the exam can no longer be cancelled.", HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Wrong id format.", HttpStatus.BAD_REQUEST);
        }
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
