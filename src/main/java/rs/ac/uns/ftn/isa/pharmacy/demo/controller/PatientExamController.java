package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamAlreadyScheduledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.GetAvailableDermatologistExamsResponse;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ExamService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/patient-exam", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientExamController {
    private final ExamService examService;

    @Autowired
    public PatientExamController(ExamService examService) {
        this.examService = examService;
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/{pharmacyId}")
    public ResponseEntity<Iterable<GetAvailableDermatologistExamsResponse>> getAvailableDermatologistExamsForPharmacy(@PathVariable("pharmacyId") long pharmacyId) {
        ArrayList<GetAvailableDermatologistExamsResponse> response = new ArrayList<>();
        examService.getAvailableDermatologistExamsForPharmacy(pharmacyId).forEach(it ->
                response.add(new GetAvailableDermatologistExamsResponse(it.getExam(), it.getDermatologist())));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    @PutMapping("/")
    public ResponseEntity<String> scheduleDermatologistExam(@RequestBody Long examId) {
        try {
            examService.scheduleDermatologistExam(examId, getSignedInUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The exam you've tried to schedule does not exist.", HttpStatus.BAD_REQUEST);
        } catch (ExamAlreadyScheduledException e) {
            return new ResponseEntity<>("Sorry, the exam is no longer available.", HttpStatus.BAD_REQUEST);
        }
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
