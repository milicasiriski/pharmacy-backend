package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;

public interface VacationService {
    Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation();
    Iterable<VacationTimeRequestDermatologist> getAllDermatologistsVacation();
}
