package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;

import java.util.List;

public interface VacationService {
    Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation();
    List<VacationTimeRequestDermatologist> getAllDermatologistsVacation();
}
