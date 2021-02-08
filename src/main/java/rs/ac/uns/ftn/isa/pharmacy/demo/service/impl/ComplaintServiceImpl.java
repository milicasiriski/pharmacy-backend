package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.ComplaintResolvedException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.ComplaintMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ComplaintAnswerDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ComplaintService;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final PharmacistRepository pharmacistRepository;
    private final DermatologistRepository dermatologistRepository;
    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final MailService<String> mailService;

    @Autowired
    public ComplaintServiceImpl(PharmacistRepository pharmacistRepository, DermatologistRepository dermatologistRepository, ComplaintRepository complaintRepository, UserRepository userRepository, MailService<String> mailService) {
        this.pharmacistRepository = pharmacistRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public List<PharmacistDto> getPharmacists() {

        Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Pharmacist> pharmacists = pharmacistRepository.getByPatientsId(patient.getId());
        List<PharmacistDto> dtos = new ArrayList<>();
        pharmacists.forEach(pharmacist -> dtos.add(new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(),
                pharmacist.getRating(), pharmacist.getId())));
        return dtos;
    }

    @Override
    public List<DermatologistDto> getDermatologists() {
        Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Dermatologist> dermatologists = dermatologistRepository.getByPatientsId(patient.getId());
        List<DermatologistDto> dtos = new ArrayList<>();
        dermatologists.forEach(dermatologist -> dtos.add(new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(),
                dermatologist.getRating(), dermatologist.getId())));
        System.out.println(dtos);
        return dtos;
    }

    @Override
    public void makeAComplaint(ComplaintDto dto) {
        Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findById(dto.getStaffId());
        if (userOptional.isEmpty()) {
            throw new BadUserInformationException();
        }
        complaintRepository.save(new Complaint(patient, userOptional.get(), dto.getComplaintText(), false));
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
    public void resolveComplaint(ComplaintAnswerDto dto) throws Exception{
        Optional<Complaint> optionalComplaint =complaintRepository.findById(dto.getId());
        if(optionalComplaint.isEmpty()){
            throw new BadRequestException();
        }
        Complaint complaint = optionalComplaint.get();
        if(complaint.isResolved()){
            throw new ComplaintResolvedException();
        }
        complaint.setAnswerText(dto.getAnswerText());
        complaint.setResolved(true);
        complaintRepository.save(complaint);
        sendMail(complaint, dto);
    }


    private void sendMail(Complaint complaint, ComplaintAnswerDto dto) throws MessagingException {
        mailService.sendMail(complaint.getPatient().getEmail(),dto.getAnswerText(),new ComplaintMailFormatter());
    }


}
