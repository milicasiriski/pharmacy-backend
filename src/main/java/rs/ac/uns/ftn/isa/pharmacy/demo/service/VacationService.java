package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationRequestDermatologistDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationRequestPharmacistDTO;

import javax.mail.MessagingException;

public interface VacationService {
    Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation();
    Iterable<VacationTimeRequestDermatologist> getAllDermatologistsVacation();
    void sendVacationResponsePharmacist(VacationDto vacationDto) throws MessagingException;
    void sendVacationResponseDermatologist(VacationDto vacationDto) throws MessagingException;
    void sendVacationRequestDermatologist(VacationRequestDermatologistDTO vacationRequestDermatologistDTO, Dermatologist dermatologist);
    void sendVacationRequestPharmacist(VacationRequestPharmacistDTO vacationRequestPharmacistDTO, Pharmacist pharmacist);
}
