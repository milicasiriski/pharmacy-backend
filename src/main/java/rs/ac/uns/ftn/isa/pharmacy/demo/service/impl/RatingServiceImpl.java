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
        Patient patient = getSignedInUser();
        ratings.forEach(submitRatingDto -> {
            Medicine medicine = getMedicineById(submitRatingDto.getId());
            RatingMedicine ratingMedicine = new RatingMedicine(patient, submitRatingDto.getRating(), medicine);
            ratingMedicineRepository.save(ratingMedicine);
        });
    }

    @Override
    public void saveDermatologistRatings(List<SubmitRatingDto> ratings) {
        Patient patient = getSignedInUser();
        ratings.forEach(submitRatingDto -> {
            Dermatologist dermatologist = getDermatologistId(submitRatingDto.getId());
            RatingDermatologist ratingDermatologist = new RatingDermatologist(patient, submitRatingDto.getRating(), dermatologist);
            ratingDermatologistRepository.save(ratingDermatologist);
        });
    }

    @Override
    public void savePharmacistRatings(List<SubmitRatingDto> ratings) throws EntityNotFoundException {
        Patient patient = getSignedInUser();
        ratings.forEach(submitRatingDto -> {
            Pharmacist pharmacist = getPharmacistById(submitRatingDto.getId());
            RatingPharmacist ratingPharmacist = new RatingPharmacist(patient, submitRatingDto.getRating(), pharmacist);
            ratingPharmacistRepository.save(ratingPharmacist);
        });
    }

    @Override
    public void savePharmacyRatings(List<SubmitRatingDto> ratings) {
        Patient patient = getSignedInUser();
        ratings.forEach(submitRatingDto -> {
            Pharmacy pharmacy = getPharmacyById(submitRatingDto.getId());
            RatingPharmacy ratingPharmacy = new RatingPharmacy(patient, submitRatingDto.getRating(), pharmacy);
            ratingPharmacyRepository.save(ratingPharmacy);
        });
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

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
