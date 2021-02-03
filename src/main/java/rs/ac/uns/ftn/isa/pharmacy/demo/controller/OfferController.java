package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
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

    @PreAuthorize("hasRole('ROLE_SUPPLIER')")// NOSONAR the focus of this project is not on web security
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

    @PreAuthorize("hasRole('ROLE_SUPPLIER')")// NOSONAR the focus of this project is not on web security
    @PostMapping("/update")
    public ResponseEntity<String> updateOffer(@RequestBody OfferDto OfferDto) {
        try {
            this.offerService.updateOffer(OfferDto);
            return new ResponseEntity<>("Offer saved successfully!", HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            badRequestException.printStackTrace();
            return new ResponseEntity<>("You cant change this offer!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Sorry, there has been a mistake on server.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_SUPPLIER')")// NOSONAR the focus of this project is not on web security
    @GetMapping("/medicine")
    public ResponseEntity<List<MedicineAmountDto>> getSuppliersMedicine() {
        try {
            List<MedicineAmountDto> amountDtos = this.offerService.getMedicinesAmount();
            return new ResponseEntity<>(amountDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_SUPPLIER')")// NOSONAR the focus of this project is not on web security
    @GetMapping("/")
    public ResponseEntity<List<OfferDto>> getAllOffers() {
        try {
            List<OfferDto> offerDtos = this.offerService.getAllOffers();
            return new ResponseEntity<>(offerDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
