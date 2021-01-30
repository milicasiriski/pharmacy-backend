package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NotAPatientException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.AccountActivationLinkMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Authority;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.User;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthorityService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPatientService;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class RegisterPatientServiceImpl implements RegisterPatientService {

    private final UserRepository userRepository;
    private final AuthorityService authService;
    private final PasswordEncoder passwordEncoder;
    private final MailService<String> mailService;

    @Autowired
    public RegisterPatientServiceImpl(UserRepository userRepository,
                                      AuthorityService authService,
                                      PasswordEncoder passwordEncoder,
                                      MailService<String> mailService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public Patient register(PatientDto dto, String siteURL) throws MessagingException {

        Patient patient = dto.createPatient();
        List<Authority> auth = authService.findByname(patient.getAdministrationRole());
        patient.setAuthorities(auth);
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        String activationCode = RandomString.make(64);
        patient.setActivationCode(activationCode);
        patient = userRepository.save(patient);
        sendActivationLink(patient, siteURL);
        return patient;
    }

    @Override
    public Patient activate(String email, String activationCode) throws BadActivationCodeException, NotAPatientException {
        Patient patient = findByEmail(email);
        if (!patient.getActivationCode().equals(activationCode)) {
            throw new BadActivationCodeException();
        }
        patient.Enable();
        patient.setActivationCode(null);
        patient = this.userRepository.save(patient);
        return patient;
    }

    @Override
    public Patient findByEmail(String email) throws NotAPatientException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        if (!user.getClass().equals(Patient.class)) {
            throw new NotAPatientException();
        } else {
            return (Patient) user;
        }
    }

    @Override
    public boolean userExists(String email) {
        try {
            return findByEmail(email) != null;
        } catch (NotAPatientException e) {
            return false;
        }
    }

    private void sendActivationLink(Patient patient, String siteUrl) throws MessagingException {
        String verifyURL = siteUrl + "/activation?code=" + patient.getActivationCode() + "&email=" + patient.getEmail();
        mailService.sendMail(patient.getEmail(), verifyURL, new AccountActivationLinkMailFormatter());
    }

}
