package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;

import java.util.List;

public interface DermatologistService {

    Iterable<Dermatologist> getAllDermatologists();

    List<Dermatologist> getDermatologistsByPharmacy(Long pharmacyId);

    List<DermatologistDto> getOtherDermatologists();

    List<PharmacyDto> getDermatologistsPharmacies(long dermatologistID);
}
