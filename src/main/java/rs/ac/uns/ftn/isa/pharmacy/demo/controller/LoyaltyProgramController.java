package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LoyaltyProgramDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LoyaltyService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/loyalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoyaltyProgramController {

    private final LoyaltyService loyaltyService;

    public LoyaltyProgramController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @PostMapping("/")
    public ResponseEntity<String> update(@RequestBody LoyaltyProgramDto dto){
        try {
            loyaltyService.update(dto);
            return new ResponseEntity<>("Loyalty program updated.", HttpStatus.OK);
        }
        catch (BadRequestException badRequestException){
            return new ResponseEntity<>("You sent bad information, please try again.", HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>("Someone is currently changing loyalty program, please try again.", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<LoyaltyProgramDto> getProgram(){
        try{
            LoyaltyProgramDto loyaltyService = this.loyaltyService.getLoyaltyProgramDto();
            return new ResponseEntity<>(loyaltyService,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Scheduled(cron = "${examPoints.cron}")
    public void executeMedicineReservationCleanUp() {
        loyaltyService.givePointsForExam();
    }
}
