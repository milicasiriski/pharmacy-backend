package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RatingCalculationAndUpdateService;

@Service
public class RatingCalculationAndUpdateServiceImpl implements RatingCalculationAndUpdateService {
    private final PharmacyRepository pharmacyRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistRepository pharmacistRepository;
    private final MedicineRepository medicineRepository;
    private final RatingMedicineRepository ratingMedicineRepository;
    private final RatingDermatologistRepository ratingDermatologistRepository;
    private final RatingPharmacistRepository ratingPharmacistRepository;
    private final RatingPharmacyRepository ratingPharmacyRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RatingCalculationAndUpdateServiceImpl(PharmacyRepository pharmacyRepository, DermatologistRepository dermatologistRepository, PharmacistRepository pharmacistRepository, MedicineRepository medicineRepository, RatingMedicineRepository ratingMedicineRepository, RatingDermatologistRepository ratingDermatologistRepository, RatingPharmacistRepository ratingPharmacistRepository, RatingPharmacyRepository ratingPharmacyRepository) {
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
    public void calculateAndUpdateMedicineRatings() {
        medicineRepository.findAll().forEach(this::calculateAndUpdateMedicineRating);
    }

    @Override
    public void calculateAndUpdateDermatologistRatings() {
        dermatologistRepository.findAll().forEach(this::calculateAndUpdateDermatologistRating);
    }

    @Override
    public void calculateAndUpdatePharmacistRatings() {
        pharmacistRepository.findAll().forEach(this::calculateAndUpdatePharmacistRating);
    }

    @Override
    public void calculateAndUpdatePharmacyRatings() {
        pharmacyRepository.findAll().forEach(this::calculateAndUpdatePharmacyRating);
    }

    private void calculateAndUpdateMedicineRating(Medicine medicine) {
        Double rating = ratingMedicineRepository.findAverageRatingForMedicine(medicine.getId()).orElse(null);
        if (rating != null) {
            double oldRating = medicine.getRatings();
            medicine.setRatings(rating);
            medicineRepository.save(medicine);
            logger.info("Changed rating of medicine {} from {} to {}", medicine.getId(), oldRating, rating);
        }
    }

    private void calculateAndUpdateDermatologistRating(Dermatologist dermatologist) {
        Double rating = ratingDermatologistRepository.findAverageRatingForDermatologist(dermatologist.getId()).orElse(null);
        if (rating != null) {
            double oldRating = dermatologist.getRating();
            dermatologist.setRating(rating);
            dermatologistRepository.save(dermatologist);
            logger.info("Changed rating of dermatologist {} from {} to {}", dermatologist.getId(), oldRating, rating);
        }
    }

    private void calculateAndUpdatePharmacistRating(Pharmacist pharmacist) {
        Double rating = ratingPharmacistRepository.findAverageRatingForPharmacist(pharmacist.getId()).orElse(null);
        if (rating != null) {
            double oldRating = pharmacist.getRating();
            pharmacist.setRating(rating);
            pharmacistRepository.save(pharmacist);
            logger.info("Changed rating of pharmacist {} from {} to {}", pharmacist.getId(), oldRating, rating);
        }
    }

    private void calculateAndUpdatePharmacyRating(Pharmacy pharmacy) {
        Double rating = ratingPharmacyRepository.findAverageRatingForPharmacy(pharmacy.getId()).orElse(null);
        if (rating != null) {
            double oldRating = pharmacy.getRating();
            pharmacy.setRating(rating);
            pharmacyRepository.save(pharmacy);
            logger.info("Changed rating of pharmacy {} from {} to {}", pharmacy.getId(), oldRating, rating);
        }
    }
}
