package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SubmitRatingDto;

import java.util.List;

public interface RatingService {
    Iterable<Pharmacy> getPharmacies();

    Iterable<Dermatologist> getDermatologists();

    Iterable<Pharmacist> getPharmacists();

    Iterable<Medicine> getMedicine();

    void saveMedicineRatings(List<SubmitRatingDto> ratings);

    void saveDermatologistRatings(List<SubmitRatingDto> ratings);

    void savePharmacistRatings(List<SubmitRatingDto> ratings);

    void savePharmacyRatings(List<SubmitRatingDto> ratings);
}
