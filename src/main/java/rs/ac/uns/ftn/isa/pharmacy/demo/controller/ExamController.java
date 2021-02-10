package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamIntervalIsNotInShiftIntervalException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ExamIntervalIsOverlapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ShiftIsNotDefinedException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ExamService;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;

@RestController
@RequestMapping(value = "/exam", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> createExam(@RequestBody PharmacyAdminExamDto pharmacyAdminExamDto) {
        try {
            examService.createExam(pharmacyAdminExamDto);
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

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @DeleteMapping("/delete/{examId}")
    public ResponseEntity<String> deleteExam(@PathVariable("examId") Long examId) {
        try {
            examService.deleteExam(examId);
            return new ResponseEntity<>("Exam successfully deleted!", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Exam does not exist!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}