package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.TimeIntervalDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserRegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthorityService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RegisterPharmacistService;

import java.util.*;

@Service
public class RegisterPharmacistServiceImpl implements RegisterPharmacistService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityService authService;

    @Autowired
    public RegisterPharmacistServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthorityService authService) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public void register(UserRegistrationDto registrationInfo) {
        Address address = new Address(registrationInfo.getCountry(), registrationInfo.getStreet(), registrationInfo.getCity());
        PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Pharmacist pharmacist = new Pharmacist(registrationInfo.getEmail(), passwordEncoder.encode(registrationInfo.getPassword()),
                registrationInfo.getName(), registrationInfo.getSurname(), address, admin.getPharmacy(), generateShifts(registrationInfo.getShifts()));

        List<Authority> auth = authService.findByname(pharmacist.getAdministrationRole());
        pharmacist.setAuthorities(auth);
        userRepository.save(pharmacist);
    }

    private Map<DaysOfWeek, TimeInterval> generateShifts(List<TimeIntervalDto> timeIntervals) {
        Map<DaysOfWeek, TimeInterval> shifts = new HashMap<>();
        for (int i = 0; i <= 6; i++) {
            TimeInterval shift = generateShiftTimeInterval(timeIntervals.get(i));
            shifts.put(DaysOfWeek.values()[i], shift);
        }

        return shifts;
    }

    private TimeInterval generateShiftTimeInterval(TimeIntervalDto timeIntervalDto) {
        Calendar shiftStart = Calendar.getInstance();
        Calendar shiftEnd = Calendar.getInstance();

        shiftStart.setTime(timeIntervalDto.getStart());
        shiftStart.set(Calendar.SECOND, 0);
        shiftStart.set(Calendar.MILLISECOND, 0);

        shiftEnd.setTime(timeIntervalDto.getEnd());
        shiftEnd.set(Calendar.SECOND, 0);
        shiftEnd.set(Calendar.MILLISECOND, 0);

        return new TimeInterval(shiftStart, shiftEnd);
    }
}
