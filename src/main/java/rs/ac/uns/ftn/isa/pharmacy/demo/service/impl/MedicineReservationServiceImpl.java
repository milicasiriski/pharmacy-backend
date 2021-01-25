package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MedicineReservationConfirmMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicineReservationServiceImpl implements MedicineReservationService {
    private MedicineRepository medicineRepository;
    private MedicineReservationRepository medicineReservationRepository;
    private PharmacyRepository pharmacyRepository;
    private MailService<MedicineReservationEmailParams> mailService;

    @Autowired
    public MedicineReservationServiceImpl(
            MedicineRepository medicineRepository,
            MedicineReservationRepository medicineReservationRepository,
            PharmacyRepository pharmacyRepository,
            Environment env,
            JavaMailSender javaMailSender) {
        this.medicineRepository = medicineRepository;
        this.medicineReservationRepository = medicineReservationRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.mailService = new MailService<>(env, javaMailSender, new MedicineReservationConfirmMailFormatter());
    }

    @Override
    public Iterable<Medicine> getAllMedicine() {
        return medicineRepository.findAll();
    }

    @Override
    public Iterable<Pharmacy> getPharmaciesWithMedicineOnStock(Long medicineId) {
        return pharmacyRepository.findWithMedicineOnStock(medicineId);
    }

    @Override
    public boolean isReservationValid(MedicineReservationDto medicineReservationDto) {
        try {
            Medicine medicine = getMedicineById(medicineReservationDto.getMedicineId());
            Pharmacy pharmacy = getPharmacyById(medicineReservationDto.getPharmacyId());
            if (pharmacy.getMedicine().containsKey(medicine)) {
                return pharmacy.getMedicine().get(medicine).getStock() > 0;
            } else {
                return false;
            }
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    @Override
    public void confirmReservation(MedicineReservationDto medicineReservationDto, Patient patient) throws MessagingException {
        Medicine medicine = getMedicineById(medicineReservationDto.getMedicineId());
        Pharmacy pharmacy = getPharmacyById(medicineReservationDto.getPharmacyId());

        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTime(medicineReservationDto.getExpirationDate());

        String uniqueReservationNumber = UUID.randomUUID().toString();
        MedicineReservation medicineReservation = new MedicineReservation(medicine, patient, expirationDate, uniqueReservationNumber);
        medicineReservationRepository.save(medicineReservation);

        MedicineStatus medicineStatus = pharmacy.getMedicine().get(medicine);
        int currentStock = medicineStatus.getStock();
        medicineStatus.setStock(currentStock - 1);
        pharmacy.getMedicine().put(medicine, medicineStatus);
        pharmacyRepository.save(pharmacy);

        MedicineReservationEmailParams params = new MedicineReservationEmailParams(medicine.getName(),
                medicineReservationDto.getExpirationDate(),
                pharmacy.getName(),
                pharmacy.getAddress(),
                uniqueReservationNumber);
        mailService.sendMail(patient.getEmail(), params);
    }

    private Pharmacy getPharmacyById(Long pharmacyId) throws EntityNotFoundException {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(pharmacyId);
        if (optionalPharmacy.isPresent()) {
            return optionalPharmacy.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Medicine getMedicineById(Long medicineId) throws EntityNotFoundException {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        if (optionalMedicine.isPresent()) {
            return optionalMedicine.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}