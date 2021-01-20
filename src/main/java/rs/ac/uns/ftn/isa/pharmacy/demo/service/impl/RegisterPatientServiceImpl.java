package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadActivationCodeException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Authority;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthorityService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPatientService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.EmailSubjectMaker;

import java.io.IOException;
import java.util.List;

@Service
public class RegisterPatientServiceImpl implements RegisterPatientService {

    private UserRepository userRepository;
    private AuthorityService authService;
    private JavaMailSender javaMailSender;
    private Environment env;

    @Autowired
    public RegisterPatientServiceImpl(UserRepository userRepository,
                                      AuthorityService authService,
                                      JavaMailSender javaMailSender,
                                      Environment env) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.javaMailSender = javaMailSender;
        this.env = env;
    }


    @Override
    public Patient register(PatientDTO dto, String siteURL) throws MailException{

        Patient patient = dto.createPatient();
        List<Authority> auth = authService.findByname(patient.getAdministrationRole());
        patient.setAuthorities(auth);
        String activationCode = RandomString.make(64);
        patient.setActivationCode(activationCode);
        System.out.println(patient);
        patient = (Patient) userRepository.save(patient);
        sendActivationLink(patient, siteURL);
        return patient;
    }

    @Override
    public Patient activate(String email, String activationCode) throws BadActivationCodeException {
        Patient patient = findByEmail(email);
        if (patient.getActivationCode() != activationCode) {
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

    @Async
    public void sendActivationLink(Patient patient, String siteUrl) throws MailException {
        EmailSubjectMaker emailSubjectMaker = new EmailSubjectMaker();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(patient.getEmail());
        mail.setFrom(this.env.getProperty("spring.mail.username"));
        mail.setSubject("Wellcome to ®™PharmacyManager!");
        String verifyURL = siteUrl + "/activation?code=" + patient.getActivationCode() + "&email=" + patient.getEmail();
        mail.setText(emailSubjectMaker.makeActivationHtml(verifyURL));
        javaMailSender.send(mail);

        System.out.println("Email sent!");
    }


}
