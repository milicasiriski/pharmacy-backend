package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
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
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class RegisterPatientServiceImpl implements RegisterPatientService {

    private UserRepository userRepository;
    private AuthorityService authService;
    private JavaMailSender javaMailSender;
    private PasswordEncoder passwordEncoder;
    private Environment env;


    @Autowired
    public RegisterPatientServiceImpl(UserRepository userRepository,
                                      AuthorityService authService,
                                      JavaMailSender javaMailSender,
                                      Environment env,
                                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.javaMailSender = javaMailSender;
        this.env = env;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Patient register(PatientDTO dto, String siteURL) throws MessagingException {

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

    @Async
    public void sendActivationLink(Patient patient, String siteUrl) throws MessagingException{
        EmailSubjectMaker emailSubjectMaker = new EmailSubjectMaker();
        String verifyURL = siteUrl + "/activation?code=" + patient.getActivationCode() + "&email=" + patient.getEmail();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message);
        mail.setTo(patient.getEmail());
        mail.setFrom(this.env.getProperty("spring.mail.username"));
        mail.setSubject("Wellcome to ®™PharmacyManager!");
        mail.setText(emailSubjectMaker.makeActivationHtml(verifyURL));
        javaMailSender.send(message);
        System.out.println("Email sent!");
    }


}
