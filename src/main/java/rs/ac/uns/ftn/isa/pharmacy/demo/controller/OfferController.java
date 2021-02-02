package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OrderException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OfferService;

import java.util.List;

@RestController
@RequestMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @PostMapping("/")
    public ResponseEntity<String> saveOffer(@RequestBody OfferDto OfferDto) {
        try {
            this.offerService.addNewOffer(OfferDto);
            return new ResponseEntity<>("Offer saved successfully!", HttpStatus.OK);
        } catch (NoMedicineFoundException noMedicineFoundException) {
            return new ResponseEntity<>("Sorry, you dont have enough medicine!", HttpStatus.BAD_REQUEST);
        } catch (OrderException orderException) {
            return new ResponseEntity<>("Sorry, order you sent is empty!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Sorry, there has been a mistake on server.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @GetMapping("/medicine")
    public ResponseEntity<List<MedicineAmountDto>> getSuppliersMedicine() {
        try {
            List<MedicineAmountDto> amountDtos = this.offerService.getMedicinesAmount();
            return new ResponseEntity<>(amountDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
