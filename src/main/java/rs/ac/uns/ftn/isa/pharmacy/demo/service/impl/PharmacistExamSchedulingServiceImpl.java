package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacistExamSchedulingService;

import java.util.Date;

@Service
public class PharmacistExamSchedulingServiceImpl implements PharmacistExamSchedulingService {
    private final PharmacyRepository pharmacyRepository;

    public PharmacistExamSchedulingServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Iterable<Pharmacy> getPharmaciesWithAvailableAppointments(Date dateTime) {
        return null;
    }
}
