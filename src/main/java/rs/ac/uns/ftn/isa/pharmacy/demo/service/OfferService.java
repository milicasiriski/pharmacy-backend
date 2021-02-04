package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;

import java.util.List;

public interface OfferService {
    Offer addNewOffer(OfferDto dto);

    List<MedicineAmountDto> getMedicinesAmount();

    List<OfferDto> getAllOffers();

    Offer updateOffer(OfferDto offerDto);

    List<List<OfferDto>> getAllOffersByOrders();

    void acceptOffer(Long offerId);
}
