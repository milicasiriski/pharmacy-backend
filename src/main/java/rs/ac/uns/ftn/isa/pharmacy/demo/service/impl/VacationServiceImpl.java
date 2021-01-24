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
        if (vacation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        vacation.setApproved(vacationDto.isApproved());
        vacation.setRejectedReason(vacationDto.getReason());
        vacation.setStatus("Responded");
        vacationRepository.save(vacation);

        VacationTimeRequestPharmacist vacationTimeRequestPharmacist = pharmacistVacationRepository.findById(vacationDto.getId()).orElse(null);
        if (vacationTimeRequestPharmacist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        sendVacationStatusToEmail(vacationTimeRequestPharmacist.getPharmacist(), vacationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public void sendVacationResponseDermatologist(VacationDto vacationDto) {
    }

    @Async
    public void sendVacationStatusToEmail(Pharmacist pharmacist, VacationDto vacationDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message);
        mail.setTo(pharmacist.getEmail());
        mail.setFrom(this.env.getProperty("spring.mail.username"));
        mail.setSubject("Vacation");

        String vacationStatus;

        if (vacationDto.isApproved()) {
            vacationStatus = "Accepted!";
        } else {
            vacationStatus = "Rejected! " + "Reason:" + vacationDto.getReason();
        }

        mail.setText("Your vacation request is" + vacationStatus);
        javaMailSender.send(message);
        System.out.println("Email sent!");
    }
}
