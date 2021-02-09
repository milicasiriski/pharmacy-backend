package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExaminerRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SubmitRatingDto;

import java.util.List;

public interface RatingService {
    Iterable<PharmacyRatingDto> getPharmacies();

    Iterable<ExaminerRatingDto> getDermatologists();

    Iterable<ExaminerRatingDto> getPharmacists();

    Iterable<MedicineRatingDto> getMedicine();

    void saveMedicineRatings(List<SubmitRatingDto> ratings);

    void saveDermatologistRatings(List<SubmitRatingDto> ratings);

    void savePharmacistRatings(List<SubmitRatingDto> ratings);

    void savePharmacyRatings(List<SubmitRatingDto> ratings);
}
