package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PrescriptionUsedException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.EPrescriptionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.QRResultDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.QRService;

import java.util.List;

@Controller
@RequestMapping(value = "/qr", produces = MediaType.APPLICATION_JSON_VALUE)
public class QRMedicineController {

    @Qualifier("QRServiceImpl")
    private final QRService qrService;

    @Autowired
    public QRMedicineController(QRService qrService) {
        this.qrService = qrService;
    }

    @PostMapping("/pharmacies")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<List<QRResultDto>> findPharmacies(@RequestBody EPrescriptionDto dto) {
        try {
            List<QRResultDto> res = qrService.findPharmacies(dto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (BadUserInformationException badUserInformationException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoMedicineFoundException noMedicineFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buy")
    @PreAuthorize("hasRole('ROLE_PATIENT')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> buyMedicines(@RequestBody QRResultDto dto) {
        try {
            qrService.buy(dto);
            return new ResponseEntity<>("Medicine successfully bought.", HttpStatus.OK);
        } catch (PrescriptionUsedException prescriptionUsedException) {
            return new ResponseEntity<>("Sorry, you can't use one prescription multiple times."
                    , HttpStatus.BAD_REQUEST);
        } catch (NoMedicineFoundException noMedicineFoundException) {
            return new ResponseEntity<>("Sorry, while you were waiting, we run out of selected medicines.",
                    HttpStatus.BAD_REQUEST);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>("Please don't try buying medicine at the same time."
                    , HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Sorry, you sent a bad request, used prescription for second time," +
                    " or something else went wrong.",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
