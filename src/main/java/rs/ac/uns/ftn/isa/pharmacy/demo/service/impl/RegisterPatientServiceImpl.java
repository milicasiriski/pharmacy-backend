package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.AccountActivationLinkMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Authority;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthorityService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPatientService;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class RegisterPatientServiceImpl implements RegisterPatientService {

    private UserRepository userRepository;
    private AuthorityService authService;
    private PasswordEncoder passwordEncoder;
    private MailService<String> mailService;

    @Autowired
    public RegisterPatientServiceImpl(UserRepository userRepository,
                                      AuthorityService authService,
                                      PasswordEncoder passwordEncoder,
                                      JavaMailSender javaMailSender,
                                      Environment env) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = new MailService<>(env, javaMailSender, new AccountActivationLinkMailFormatter());
    }

    @Override
    public Patient register(PatientDto dto, String siteURL) throws MessagingException {

        Patient patient = dto.createPatient();
        List<Authority> auth = authService.findByname(patient.getAdministrationRole());
        patient.setAuthorities(auth);
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        String activationCode = RandomString.make(64);
        patient.setActivationCode(activationCode);
        patient = (Patient) userRepository.save(patient);
        sendActivationLink(patient, siteURL);
        return patient;
    }

    @Override
    public Patient activate(String email, String activationCode) throws BadActivationCodeException {
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
    public Patient findByEmail(String email) {
        return (Patient) userRepository.findByEmail(email);
    }

    private void sendActivationLink(Patient patient, String siteUrl) throws MessagingException {
        String verifyURL = siteUrl + "/activation?code=" + patient.getActivationCode() + "&email=" + patient.getEmail();
        mailService.sendMail(patient.getEmail(), verifyURL);
    }

}
