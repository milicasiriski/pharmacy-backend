package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SubmitRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RatingService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {
    private final PharmacyRepository pharmacyRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistRepository pharmacistRepository;
    private final MedicineRepository medicineRepository;
    private final RatingMedicineRepository ratingMedicineRepository;
    private final RatingDermatologistRepository ratingDermatologistRepository;
    private final RatingPharmacistRepository ratingPharmacistRepository;
    private final RatingPharmacyRepository ratingPharmacyRepository;

    @Autowired
    public RatingServiceImpl(PharmacyRepository pharmacyRepository, DermatologistRepository dermatologistRepository, PharmacistRepository pharmacistRepository, MedicineRepository medicineRepository, RatingMedicineRepository ratingMedicineRepository, RatingDermatologistRepository ratingDermatologistRepository, RatingPharmacistRepository ratingPharmacistRepository, RatingPharmacyRepository ratingPharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.medicineRepository = medicineRepository;
        this.ratingMedicineRepository = ratingMedicineRepository;
        this.ratingDermatologistRepository = ratingDermatologistRepository;
        this.ratingPharmacistRepository = ratingPharmacistRepository;
        this.ratingPharmacyRepository = ratingPharmacyRepository;
    }

    @Override
    public Iterable<Pharmacy> getPharmacies() {
        long patientId = getSignedInUser().getId();
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdPurchase(patientId));
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdDermatologistsExam(patientId));
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdPharmacistsExam(patientId));
        return pharmacies.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Iterable<Dermatologist> getDermatologists() {
        return dermatologistRepository.getByPatientsId(getSignedInUser().getId());
    }

    @Override
    public Iterable<Pharmacist> getPharmacists() {
        return pharmacistRepository.getByPatientsId(getSignedInUser().getId());
    }

    @Override
    public Iterable<Medicine> getMedicine() {
        return medicineRepository.findRateableByPatientId(getSignedInUser().getId());
    }

    @Override
    public void saveMedicineRatings(List<SubmitRatingDto> ratings) {
        ratings.forEach(this::saveRatingMedicine);
    }

    @Override
    public void saveDermatologistRatings(List<SubmitRatingDto> ratings) {
        ratings.forEach(this::saveRatingDermatologist);
    }

    @Override
    public void savePharmacistRatings(List<SubmitRatingDto> ratings) throws EntityNotFoundException {
        ratings.forEach(this::saveRatingPharmacist);
    }

    @Override
    public void savePharmacyRatings(List<SubmitRatingDto> ratings) {
        ratings.forEach(this::saveRatingPharmacy);
    }

    private void saveRatingMedicine(SubmitRatingDto rating) {
        Patient patient = getSignedInUser();
        RatingMedicine ratingMedicine = getExistingRatingMedicine(patient.getId(), rating.getId());
        if (ratingMedicine == null) {
            ratingMedicine = new RatingMedicine(patient, rating.getRating(), getMedicineById(rating.getId()));
        } else {
            ratingMedicine.setRating(rating.getRating());
        }
        ratingMedicineRepository.save(ratingMedicine);
    }

    private void saveRatingDermatologist(SubmitRatingDto rating) {
        Patient patient = getSignedInUser();
        RatingDermatologist ratingDermatologist = getExistingRatingDermatologist(patient.getId(), rating.getId());
        if (ratingDermatologist == null) {
            ratingDermatologist = new RatingDermatologist(patient, rating.getRating(), getDermatologistId(rating.getId()));
        } else {
            ratingDermatologist.setRating(rating.getRating());
        }
        ratingDermatologistRepository.save(ratingDermatologist);
    }

    private void saveRatingPharmacist(SubmitRatingDto rating) {
        Patient patient = getSignedInUser();
        RatingPharmacist ratingDermatologist = getExistingRatingPharmacist(patient.getId(), rating.getId());
        if (ratingDermatologist == null) {
            ratingDermatologist = new RatingPharmacist(patient, rating.getRating(), getPharmacistById(rating.getId()));
        } else {
            ratingDermatologist.setRating(rating.getRating());
        }
        ratingPharmacistRepository.save(ratingDermatologist);
    }

    private void saveRatingPharmacy(SubmitRatingDto rating) {
        Patient patient = getSignedInUser();
        RatingPharmacy ratingPharmacy = getExistingRatingPharmacy(patient.getId(), rating.getId());
        if (ratingPharmacy == null) {
            ratingPharmacy = new RatingPharmacy(patient, rating.getRating(), getPharmacyById(rating.getId()));
        } else {
            ratingPharmacy.setRating(rating.getRating());
        }
        ratingPharmacyRepository.save(ratingPharmacy);
    }

    private Medicine getMedicineById(long id) throws EntityNotFoundException {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()) {
            return optionalMedicine.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Dermatologist getDermatologistId(long id) throws EntityNotFoundException {
        Optional<Dermatologist> optionalDermatologist = dermatologistRepository.findById(id);
        if (optionalDermatologist.isPresent()) {
            return optionalDermatologist.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Pharmacist getPharmacistById(long id) throws EntityNotFoundException {
        Optional<Pharmacist> optionalPharmacist = pharmacistRepository.findById(id);
        if (optionalPharmacist.isPresent()) {
            return optionalPharmacist.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Pharmacy getPharmacyById(long id) throws EntityNotFoundException {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(id);
        if (optionalPharmacy.isPresent()) {
            return optionalPharmacy.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private RatingMedicine getExistingRatingMedicine(long patientId, long medicineId) {
        Optional<RatingMedicine> optionalRating = ratingMedicineRepository.findByPatientIdAndMedicineId(patientId, medicineId);
        return optionalRating.orElse(null);
    }

    private RatingDermatologist getExistingRatingDermatologist(long patientId, long dermatologistId) {
        Optional<RatingDermatologist> optionalRating = ratingDermatologistRepository.findByPatientIdAndDermatologistId(patientId, dermatologistId);
        return optionalRating.orElse(null);
    }

    private RatingPharmacist getExistingRatingPharmacist(long patientId, long pharmacistId) {
        Optional<RatingPharmacist> optionalRating = ratingPharmacistRepository.findByPatientIdAndPharmacistId(patientId, pharmacistId);
        return optionalRating.orElse(null);
    }

    private RatingPharmacy getExistingRatingPharmacy(long patientId, long pharmacyId) {
        Optional<RatingPharmacy> optionalRating = ratingPharmacyRepository.findByPatientIdAndPharmacyId(patientId, pharmacyId);
        return optionalRating.orElse(null);
    }

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
