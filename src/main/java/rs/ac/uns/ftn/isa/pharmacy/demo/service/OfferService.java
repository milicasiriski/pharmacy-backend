package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;

public interface OfferService {
    Offer addNewOffer(OfferDto dto);
}
