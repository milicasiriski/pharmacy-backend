package rs.ac.uns.ftn.isa.pharmacy.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LogInDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserTokenState;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineService;

@RestController
@RequestMapping(value = "/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineCrudController {

    MedicineService medicineService;

    @Autowired
    public MedicineCrudController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveMedicine(@RequestBody MedicineDto dto) {
        try {
            medicineService.save(dto);
        }
        catch (Exception e){
            return new ResponseEntity<>("Sorry, you have sent a bad request!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Medicine saved!", HttpStatus.OK);
    }
}
