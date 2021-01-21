package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDto;

import javax.mail.MessagingException;

public interface RegisterPatientService extends RegisterService<PatientDto, Patient> {

    Patient activate(String email, String activationCode) throws BadActivationCodeException;

    Patient register(PatientDto dto, String siteURL) throws MessagingException;

}
