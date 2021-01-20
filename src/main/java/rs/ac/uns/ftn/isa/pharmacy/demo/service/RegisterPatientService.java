package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import org.springframework.mail.MailException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface RegisterPatientService extends RegisterService<PatientDTO, Patient> {

    Patient activate(String email, String activationCode) throws BadActivationCodeException;

    Patient register(PatientDTO dto, String siteURL) throws MessagingException;

}
