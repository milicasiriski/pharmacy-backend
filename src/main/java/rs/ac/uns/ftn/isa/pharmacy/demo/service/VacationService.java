package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;

import javax.mail.MessagingException;

public interface VacationService {
    Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation();
    Iterable<VacationTimeRequestDermatologist> getAllDermatologistsVacation();
    void sendVacationResponsePharmacist(VacationDto vacationDto) throws MessagingException;
    void sendVacationResponseDermatologist(VacationDto vacationDto) throws MessagingException;
}
