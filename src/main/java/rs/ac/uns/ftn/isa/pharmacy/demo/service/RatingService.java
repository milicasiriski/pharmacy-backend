package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;

public interface RatingService {
    Iterable<Pharmacy> getPharmacies(Patient patient);

    Iterable<Dermatologist> getDermatologists(Patient patient);

    Iterable<Pharmacist> getPharmacists(Patient patient);

    Iterable<Medicine> getMedicine(Patient patient);
}
