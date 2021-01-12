package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Authority;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthorityService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterService;

import java.util.List;

@Service
public class RegisterPatientServiceImpl implements RegisterService<PatientDTO, Patient> {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authService;

    @Autowired
    public RegisterPatientServiceImpl(UserRepository userRepository,
                                      PasswordEncoder passwordEncoder,
                                      AuthorityService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public Patient save(PatientDTO patientDTO) {
        Patient patient = patientDTO.createPatient();
        List<Authority> auth = authService.findByname(patient.getAdministrationRole());
        patient.setAuthorities(auth);
        patient = (Patient) this.userRepository.save(patient);
        return patient;
    }


    @Override
    public Patient findByEmail(String email) {
        return (Patient) userRepository.findByEmail(email);
    }


}
