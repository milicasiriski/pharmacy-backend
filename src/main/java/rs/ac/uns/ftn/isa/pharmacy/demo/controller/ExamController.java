package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ExamService;

import javax.persistence.EntityNotFoundException;

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
    public void createExam(@RequestBody PharmacyAdminExamDto pharmacyAdminExamDto) {
        examService.createExam(pharmacyAdminExamDto);
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @DeleteMapping("/delete/{examId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("examId") Long examId) {
        try {
            examService.deleteExam(examId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Exam does not exist!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}