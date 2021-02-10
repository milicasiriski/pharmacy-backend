package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PatientCannotRateThisEntity;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.RatingOutOfRangeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ExaminerRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyRatingDto;
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
    public Iterable<PharmacyRatingDto> getPharmacies() {
        long patientId = getSignedInUser().getId();
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdPurchase(patientId));
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdDermatologistsExam(patientId));
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdPharmacistsExam(patientId));

        List<PharmacyRatingDto> result = new ArrayList<>();
        pharmacies.stream().distinct().collect(Collectors.toList()).forEach(pharmacy ->
                result.add(new PharmacyRatingDto(pharmacy, getExistingRatingPharmacy(patientId, pharmacy.getId()))));
        return result;
    }

    @Override
    public Iterable<ExaminerRatingDto> getDermatologists() {
        long patientId = getSignedInUser().getId();
        List<ExaminerRatingDto> result = new ArrayList<>();
        dermatologistRepository.getByPatientsId(patientId).forEach(dermatologist ->
                result.add(new ExaminerRatingDto(dermatologist, getExistingRatingDermatologist(patientId, dermatologist.getId()))));
        return result;
    }

    @Override
    public Iterable<ExaminerRatingDto> getPharmacists() {
        long patientId = getSignedInUser().getId();
        List<ExaminerRatingDto> result = new ArrayList<>();
        pharmacistRepository.getByPatientsId(patientId).forEach(pharmacist ->
                result.add(new ExaminerRatingDto(pharmacist, getExistingRatingPharmacist(patientId, pharmacist.getId()))));
        return result;
    }

    @Override
    public Iterable<MedicineRatingDto> getMedicine() {
        long patientId = getSignedInUser().getId();
        List<MedicineRatingDto> result = new ArrayList<>();
        medicineRepository.findRateableByPatientId(patientId).forEach(medicine ->
                result.add(new MedicineRatingDto(medicine, getExistingRatingMedicine(patientId, medicine.getId()))));
        return result;
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

    private void saveRatingMedicine(SubmitRatingDto rating) throws EntityNotFoundException, RatingOutOfRangeException, PatientCannotRateThisEntity {
        validateRating(rating.getRating());
        Patient patient = getSignedInUser();
        if (!medicineRepository.canPatientRateMedicine(patient.getId(), rating.getId())) {
            throw new PatientCannotRateThisEntity();
        }
        RatingMedicine ratingMedicine = getExistingRatingMedicine(patient.getId(), rating.getId());
        if (ratingMedicine == null) {
            ratingMedicine = new RatingMedicine(patient, rating.getRating(), getMedicineById(rating.getId()));
        } else {
            ratingMedicine.setStars(rating.getRating());
        }
        ratingMedicineRepository.save(ratingMedicine);
    }

    private void saveRatingDermatologist(SubmitRatingDto rating) throws EntityNotFoundException, RatingOutOfRangeException, PatientCannotRateThisEntity {
        validateRating(rating.getRating());
        Patient patient = getSignedInUser();
        if (!dermatologistRepository.canPatientRateDermatologist(patient.getId(), rating.getId())) {
            throw new PatientCannotRateThisEntity();
        }
        RatingDermatologist ratingDermatologist = getExistingRatingDermatologist(patient.getId(), rating.getId());
        if (ratingDermatologist == null) {
            ratingDermatologist = new RatingDermatologist(patient, rating.getRating(), getDermatologistId(rating.getId()));
        } else {
            ratingDermatologist.setStars(rating.getRating());
        }
        ratingDermatologistRepository.save(ratingDermatologist);
    }

    private void saveRatingPharmacist(SubmitRatingDto rating) throws EntityNotFoundException, RatingOutOfRangeException, PatientCannotRateThisEntity {
        validateRating(rating.getRating());
        Patient patient = getSignedInUser();
        if (!pharmacistRepository.canPatientRatePharmacist(patient.getId(), rating.getId())) {
            throw new PatientCannotRateThisEntity();
        }
        RatingPharmacist ratingDermatologist = getExistingRatingPharmacist(patient.getId(), rating.getId());
        if (ratingDermatologist == null) {
            ratingDermatologist = new RatingPharmacist(patient, rating.getRating(), getPharmacistById(rating.getId()));
        } else {
            ratingDermatologist.setStars(rating.getRating());
        }
        ratingPharmacistRepository.save(ratingDermatologist);
    }

    private void saveRatingPharmacy(SubmitRatingDto rating) throws EntityNotFoundException, RatingOutOfRangeException, PatientCannotRateThisEntity {
        validateRating(rating.getRating());
        Patient patient = getSignedInUser();
        if (!pharmacyRepository.canPatientRatePharmacy(patient.getId(), rating.getId())) {
            throw new PatientCannotRateThisEntity();
        }
        RatingPharmacy ratingPharmacy = getExistingRatingPharmacy(patient.getId(), rating.getId());
        if (ratingPharmacy == null) {
            ratingPharmacy = new RatingPharmacy(patient, rating.getRating(), getPharmacyById(rating.getId()));
        } else {
            ratingPharmacy.setStars(rating.getRating());
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

    private void validateRating(int rating) throws RatingOutOfRangeException {
        if (rating < 1 || rating > 5) {
            throw new RatingOutOfRangeException();
        }
    }
}
