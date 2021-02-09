package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SubmitRatingDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RatingService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    private final PharmacyRepository pharmacyRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistRepository pharmacistRepository;
    private final MedicineRepository medicineRepository;
    private final RatingDermatologistRepository ratingDermatologistRepository;
    private final RatingPharmacistRepository ratingPharmacistRepository;

    @Autowired
    public RatingServiceImpl(PharmacyRepository pharmacyRepository, DermatologistRepository dermatologistRepository, PharmacistRepository pharmacistRepository, MedicineRepository medicineRepository, RatingDermatologistRepository ratingDermatologistRepository, RatingPharmacistRepository ratingPharmacistRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.medicineRepository = medicineRepository;
        this.ratingDermatologistRepository = ratingDermatologistRepository;
        this.ratingPharmacistRepository = ratingPharmacistRepository;
    }

    @Override
    public Iterable<Pharmacy> getPharmacies(Patient patient) {
        // TODO: get pharmacies that patient can rate
        return null;
    }

    @Override
    public Iterable<Dermatologist> getDermatologists(Patient patient) {
        return dermatologistRepository.getByPatientsId(patient.getId());
    }

    @Override
    public Iterable<Pharmacist> getPharmacists(Patient patient) {
        return pharmacistRepository.getByPatientsId(patient.getId());
    }

    @Override
    public Iterable<Medicine> getMedicine(Patient patient) {
        return medicineRepository.findRateableByPatientId(patient.getId());
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

    private Patient getSignedInUser() {
        return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
