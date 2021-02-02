package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.VacationTimeResponseMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationTimeResponseEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.DermatologistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacistVacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.VacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.VacationService;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;


@Service
public class VacationServiceImpl implements VacationService {

    private final PharmacistVacationRepository pharmacistVacationRepository;
    private final DermatologistVacationRepository dermatologistVacationRepository;
    private final VacationRepository vacationRepository;
    private final MailService<VacationTimeResponseEmailParams> mailService;

    @Autowired
    public VacationServiceImpl(PharmacistVacationRepository pharmacistVacationRepository, DermatologistVacationRepository dermatologistVacationRepository, VacationRepository vacationRepository,
                               MailService<VacationTimeResponseEmailParams> mailService) {
        this.pharmacistVacationRepository = pharmacistVacationRepository;
        this.dermatologistVacationRepository = dermatologistVacationRepository;
        this.vacationRepository = vacationRepository;
        this.mailService = mailService;
    }

    public Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation() {
        return pharmacistVacationRepository.findAll();
    }

    public Iterable<VacationTimeRequestDermatologist> getAllDermatologistsVacation() {
        return dermatologistVacationRepository.findVacationsByPharmacyId(((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPharmacy().getId());
    }

    @Override
    public void sendVacationResponsePharmacist(VacationDto vacationDto) throws MessagingException, EntityNotFoundException {
        VacationTimeRequestPharmacist vacationTimeRequestPharmacist = pharmacistVacationRepository.findById(vacationDto.getId()).orElse(null);

        if (vacationTimeRequestPharmacist == null) {
            throw new EntityNotFoundException();
        }
        updateVacation(vacationDto, vacationTimeRequestPharmacist);
        sendVacationStatusToEmail(vacationTimeRequestPharmacist.getPharmacist(), vacationDto);
    }

    @Override
    public void sendVacationResponseDermatologist(VacationDto vacationDto) throws MessagingException, EntityNotFoundException {
        VacationTimeRequestDermatologist vacationTimeRequestDermatologist = dermatologistVacationRepository.findById(vacationDto.getId()).orElse(null);

        if (vacationTimeRequestDermatologist == null) {
            throw new EntityNotFoundException();
        }
        updateVacation(vacationDto, vacationTimeRequestDermatologist);
        sendVacationStatusToEmail(vacationTimeRequestDermatologist.getDermatologist(), vacationDto);
    }

    private void sendVacationStatusToEmail(User user, VacationDto vacationDto) throws MessagingException {
        VacationTimeResponseEmailParams params = new VacationTimeResponseEmailParams(vacationDto.isApproved(), vacationDto.getReason());
        mailService.sendMail(user.getEmail(), params, new VacationTimeResponseMailFormatter());
    }

    private void updateVacation(VacationDto vacationDto, VacationTimeRequest vacation) {
        vacation.setApproved(vacationDto.isApproved());
        vacation.setRejectedReason(vacationDto.getReason());
        vacation.setStatus("Responded");
        vacationRepository.save(vacation);
    }
}
