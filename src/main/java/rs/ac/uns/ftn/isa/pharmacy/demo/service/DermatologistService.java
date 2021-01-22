package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;

import java.util.List;

public interface DermatologistService {

    List<Dermatologist> getAllDermatologists();
    List<Dermatologist> getDermatologistsByPharmacy(String pharmacyName);
}
