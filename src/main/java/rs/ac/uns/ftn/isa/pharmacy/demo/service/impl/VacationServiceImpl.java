package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.VacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.VacationService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class VacationServiceImpl implements VacationService {

    private final PharmacistVacationRepository pharmacistVacationRepository;
    private final DermatologistVacationRepository dermatologistVacationRepository;
    private final VacationRepository vacationRepository;
    private final JavaMailSender javaMailSender;
    private final Environment env;

    @Autowired
    public VacationServiceImpl(PharmacistVacationRepository pharmacistVacationRepository, DermatologistVacationRepository dermatologistVacationRepository, VacationRepository vacationRepository,
                               JavaMailSender javaMailSender, Environment env) {
        this.pharmacistVacationRepository = pharmacistVacationRepository;
        this.dermatologistVacationRepository = dermatologistVacationRepository;
        this.vacationRepository = vacationRepository;
        this.javaMailSender = javaMailSender;
        this.env = env;
    }

    public Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation() {
        return pharmacistVacationRepository.findAll();
    }

    public Iterable<VacationTimeRequestDermatologist> getAllDermatologistsVacation() {
        return dermatologistVacationRepository.findAll();
    }

    @Override
    public ResponseEntity<Void> sendVacationResponsePharmacist(VacationDto vacationDto) throws MessagingException {
        VacationTimeRequest vacation = vacationRepository.findById(vacationDto.getId()).orElse(null);
        VacationTimeRequestPharmacist vacationTimeRequestPharmacist = pharmacistVacationRepository.findById(vacationDto.getId()).orElse(null);

        if (vacation == null || vacationTimeRequestPharmacist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        updateVacation(vacationDto, vacation);
        sendVacationStatusToEmail(vacationTimeRequestPharmacist.getPharmacist(), vacationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> sendVacationResponseDermatologist(VacationDto vacationDto) throws MessagingException {
        VacationTimeRequest vacation = vacationRepository.findById(vacationDto.getId()).orElse(null);
        VacationTimeRequestDermatologist vacationTimeRequestDermatologist = dermatologistVacationRepository.findById(vacationDto.getId()).orElse(null);

        if (vacation == null || vacationTimeRequestDermatologist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        updateVacation(vacationDto, vacation);
        sendVacationStatusToEmail(vacationTimeRequestDermatologist.getDermatologist(), vacationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Async
    public void sendVacationStatusToEmail(User user, VacationDto vacationDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message);
        mail.setTo(user.getEmail());
        mail.setFrom(Objects.requireNonNull(this.env.getProperty("spring.mail.username")));
        formatEmail(vacationDto, mail);
        javaMailSender.send(message);
    }

    private void formatEmail(VacationDto vacationDto, MimeMessageHelper mail) throws MessagingException {
        mail.setSubject("Vacation");
        String vacationStatus;
        if (vacationDto.isApproved()) {
            vacationStatus = "Accepted!";
        } else {
            vacationStatus = "Rejected! " + "Reason:" + vacationDto.getReason();
        }
        mail.setText("Your vacation request is" + vacationStatus);
    }

    private void updateVacation(VacationDto vacationDto, VacationTimeRequest vacation) {
        vacation.setApproved(vacationDto.isApproved());
        vacation.setRejectedReason(vacationDto.getReason());
        vacation.setStatus("Responded");
        vacationRepository.save(vacation);
    }
}
