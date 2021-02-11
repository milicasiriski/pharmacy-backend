package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.VacationTimeResponseMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationTimeResponseEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.VacationStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.VacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthenticationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.VacationService;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class VacationServiceImpl implements VacationService {

    private final PharmacistVacationRepository pharmacistVacationRepository;
    private final DermatologistVacationRepository dermatologistVacationRepository;
    private final VacationRepository vacationRepository;
    private final MailService<VacationTimeResponseEmailParams> mailService;
    private final AuthenticationService authenticationService;

    @Autowired
    public VacationServiceImpl(PharmacistVacationRepository pharmacistVacationRepository, DermatologistVacationRepository dermatologistVacationRepository, VacationRepository vacationRepository,
                               MailService<VacationTimeResponseEmailParams> mailService, AuthenticationService authenticationService) {
        this.pharmacistVacationRepository = pharmacistVacationRepository;
        this.dermatologistVacationRepository = dermatologistVacationRepository;
        this.vacationRepository = vacationRepository;
        this.mailService = mailService;
        this.authenticationService = authenticationService;
    }

    public Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation() {
        return pharmacistVacationRepository.findAll();
    }

    public Iterable<VacationTimeRequestDermatologist> getAllDermatologistsVacation() {
        return dermatologistVacationRepository.findVacationsByPharmacyId((
                (PharmacyAdmin) authenticationService.
                        getLoggedUser())
                .getPharmacy()
                .getId());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void sendVacationResponsePharmacist(VacationDto vacationDto) throws MessagingException, EntityNotFoundException {
        VacationTimeRequestPharmacist vacationTimeRequestPharmacist = pharmacistVacationRepository.findById(vacationDto.getId()).orElse(null);

        if (vacationTimeRequestPharmacist == null) {
            throw new EntityNotFoundException();
        }
        updateVacation(vacationDto, vacationTimeRequestPharmacist);
        sendVacationStatusToEmail(vacationTimeRequestPharmacist.getPharmacist(), vacationDto);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void sendVacationResponseDermatologist(VacationDto vacationDto) throws MessagingException, EntityNotFoundException {
        VacationTimeRequestDermatologist vacationTimeRequestDermatologist = dermatologistVacationRepository.findById(vacationDto.getId()).orElse(null);

        if (vacationTimeRequestDermatologist == null) {
            throw new EntityNotFoundException();
        }
        updateVacation(vacationDto, vacationTimeRequestDermatologist);
        sendVacationStatusToEmail(vacationTimeRequestDermatologist.getDermatologist(), vacationDto);
    }

    private void sendVacationStatusToEmail(User user, VacationDto vacationDto) throws MessagingException {
        VacationTimeResponseEmailParams params = new VacationTimeResponseEmailParams(vacationDto.getStatus(), vacationDto.getReason());
        mailService.sendMail(user.getEmail(), params, new VacationTimeResponseMailFormatter());
    }

    private void updateVacation(VacationDto vacationDto, VacationTimeRequest vacation) {
        vacation.setStatus(VacationStatus.valueOf(vacationDto.getStatus()));
        vacation.setRejectedReason(vacationDto.getReason());
        vacationRepository.save(vacation);
    }
}
