package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ComplaintResolvedException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.ComplaintMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthenticationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ComplaintService;

import javax.mail.MessagingException;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class ComplaintServiceImpl implements ComplaintService {

    private final PharmacistRepository pharmacistRepository;
    private final DermatologistRepository dermatologistRepository;
    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final MailService<String> mailService;
    private final PharmacyRepository pharmacyRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public ComplaintServiceImpl(PharmacistRepository pharmacistRepository, DermatologistRepository dermatologistRepository, ComplaintRepository complaintRepository, UserRepository userRepository, MailService<String> mailService, PharmacyRepository pharmacyRepository, AuthenticationService authenticationService) {
        this.pharmacistRepository = pharmacistRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.pharmacyRepository = pharmacyRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public List<PharmacistDto> getPharmacists() {

        Patient patient = (Patient) authenticationService.getLoggedUser();
        List<Pharmacist> pharmacists = pharmacistRepository.getByPatientsId(patient.getId());
        List<PharmacistDto> dtos = new ArrayList<>();
        pharmacists.forEach(pharmacist -> dtos.add(new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(),
                pharmacist.getRating(), pharmacist.getId())));
        return dtos;
    }

    @Override
    public List<PharmacyDto> getPharmacies() {

        Patient patient = (Patient) authenticationService.getLoggedUser();
        List<Pharmacy> pharmacies = pharmacyRepository.findPharmacyByPatientIdPurchase(patient.getId());
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdDermatologistsExam(patient.getId()));
        pharmacies.addAll(pharmacyRepository.findPharmacyByPatientIdPharmacistsExam(patient.getId()));

        List<PharmacyDto> dtos = new ArrayList<>();
        for (Pharmacy pharmacy : pharmacies) {
            PharmacyDto dto = new PharmacyDto(pharmacy);
            if (!dtos.contains(dto)) {
                dtos.add(dto);
            }
        }
        return new ArrayList<>(dtos);
    }

    @Override
    public List<DermatologistDto> getDermatologists() {
        Patient patient = (Patient) authenticationService.getLoggedUser();
        List<Dermatologist> dermatologists = dermatologistRepository.getByPatientsId(patient.getId());
        List<DermatologistDto> dtos = new ArrayList<>();
        dermatologists.forEach(dermatologist -> dtos.add(new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(),
                dermatologist.getRating(), dermatologist.getId())));
        return dtos;
    }

    @Override
    public void makeAComplaint(ComplaintDto dto) {
        Patient patient = (Patient) authenticationService.getLoggedUser();
        if (dto.getStaffId() != null) {
            Optional<User> userOptional = userRepository.findById(dto.getStaffId());
            if (userOptional.isEmpty()) {
                throw new BadUserInformationException();
            }
            complaintRepository.save(new Complaint(patient, userOptional.get(), dto.getComplaintText(), false));
        } else {
            Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(dto.getPharmacyId());
            if (pharmacyOptional.isEmpty()) {
                throw new BadUserInformationException();
            }
            complaintRepository.save(new Complaint(patient, pharmacyOptional.get(), dto.getComplaintText(), false));
        }
    }

    @Override
    public List<ComplaintDto> getUnresolvedComplains() {
        Iterable<Complaint> complaints = complaintRepository.findAllByResolved(false);
        List<ComplaintDto> dtos = new ArrayList<>();
        complaints.forEach(complaint -> {
            dtos.add(new ComplaintDto(complaint));
        });
        return dtos;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void resolveComplaint(ComplaintAnswerDto dto) throws Exception {
        try {
            Optional<Complaint> optionalComplaint = complaintRepository.findById(dto.getId());
            if (optionalComplaint.isEmpty()) {
                throw new BadRequestException();
            }
            Complaint complaint = optionalComplaint.get();
            if (complaint.isResolved()) {
                throw new ComplaintResolvedException();
            }
            complaint.setAnswerText(dto.getAnswerText());
            complaint.setResolved(true);
            complaintRepository.save(complaint);
            sendMail(complaint, dto);
        } catch (Exception e) {
            throw e;
        }

    }

    private void sendMail(Complaint complaint, ComplaintAnswerDto dto) throws MessagingException {
        mailService.sendMail(complaint.getPatient().getEmail(), dto.getAnswerText(), new ComplaintMailFormatter());
    }


}
