package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SubmitRatingDto;

import java.util.List;

public interface RatingService {
    Iterable<Pharmacy> getPharmacies(Patient patient);

    Iterable<Dermatologist> getDermatologists(Patient patient);

    Iterable<Pharmacist> getPharmacists(Patient patient);

    Iterable<Medicine> getMedicine(Patient patient);

    void saveMedicineRatings(List<SubmitRatingDto> ratings);

    void saveDermatologistRatings(List<SubmitRatingDto> ratings);

    void savePharmacistRatings(List<SubmitRatingDto> ratings);
}
