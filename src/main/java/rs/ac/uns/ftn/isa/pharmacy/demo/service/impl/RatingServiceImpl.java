package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
    private final PharmacyRepository pharmacyRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistRepository pharmacistRepository;
    private final MedicineRepository medicineRepository;

    @Autowired
    public RatingServiceImpl(PharmacyRepository pharmacyRepository, DermatologistRepository dermatologistRepository, PharmacistRepository pharmacistRepository, MedicineRepository medicineRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.medicineRepository = medicineRepository;
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
        // TODO: get medicine that patient can rate
        return null;
    }
}
