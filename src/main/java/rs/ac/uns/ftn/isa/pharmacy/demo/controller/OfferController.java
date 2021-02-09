package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OfferDeadlineHasNotExpiredException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OrderException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OfferService;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<String> saveOffer(@RequestBody OfferDto offerDto) {
        try {
            this.offerService.addNewOffer(offerDto);
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
    public ResponseEntity<String> updateOffer(@RequestBody OfferDto offerDto) {
        try {
            this.offerService.updateOffer(offerDto);
            return new ResponseEntity<>("Offer updated successfully!", HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
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

    @GetMapping("/getOffersByPharmacy")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<List<List<OfferDto>>> getOffersByOrders() {
        return ResponseEntity.ok(offerService.getAllOffersByOrders());
    }

    @PutMapping("/acceptOffer/{offerId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> acceptOffer(@PathVariable("offerId") Long offerId) {
        try {
            offerService.acceptOffer(offerId);
            return new ResponseEntity<>("Offer accepted!", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Offer does not exists!", HttpStatus.BAD_REQUEST);
        } catch (OfferDeadlineHasNotExpiredException e) {
            return new ResponseEntity<>("Deadline has not expired!", HttpStatus.BAD_REQUEST);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Problem occurred while sending email!", HttpStatus.BAD_REQUEST);
        }
    }
}
