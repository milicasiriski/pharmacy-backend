package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PrescriptionUsedException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.PatientsShoppingConfirmMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Prescription;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.EPrescriptionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientShoppingEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PrescribedMedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.QRResultDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PrescriptionRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.QRService;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class QRServiceImpl implements QRService {

    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MailService<PatientShoppingEmailParams> mailService;

    public QRServiceImpl(PharmacyRepository pharmacyRepository, MedicineRepository medicineRepository, UserRepository userRepository, PrescriptionRepository prescriptionRepository, MailService<PatientShoppingEmailParams> mailService) {
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.mailService = mailService;
    }

    @Override
    public List<QRResultDto> findPharmacies(EPrescriptionDto dto) {
        Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!patient.getName().equals(dto.getName())) {
            throw new BadUserInformationException();
        }
        Iterable<Pharmacy> pharmacies = pharmacyRepository.findAll();
        List<QRResultDto> qrResultDtos = new ArrayList<>();
        pharmacies.forEach(pharmacy -> {
            boolean hasInStock = true;
            int bill = 0;
            for (PrescribedMedicineDto medicineDto : dto.getMedicines()) {
                Medicine medicine = medicineRepository.findByUuid(medicineDto.getUuid());
                if (pharmacy.getMedicine().containsKey(medicine)) {
                    if (pharmacy.getMedicine().get(medicine).getStock() >= medicineDto.getAmount()) {
                        bill += pharmacy.getCurrentMedicinePrice(medicine);
                    } else {
                        hasInStock = false;
                        break;
                    }
                } else {
                    hasInStock = false;
                    break;
                }
            }
            if (hasInStock) {
                QRResultDto resultDto = new QRResultDto(bill, pharmacy.getAddress().getStreet(), pharmacy.getName(), dto, pharmacy.getId(), pharmacy.getRating());
                qrResultDtos.add(resultDto);
            }
        });
        if (qrResultDtos.isEmpty()) {
            throw new NoMedicineFoundException();
        }
        return qrResultDtos;
    }

    @Override
    public void buy(QRResultDto dto) throws Exception {
        if (prescriptionRepository.findById(dto.getPrescription().getId()).isPresent()) {
            throw new PrescriptionUsedException();
        }
        Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(dto.getPharmacyId());
        Map<Medicine, Integer> prescriptionMedicineAmount = new HashMap<>();

        if (pharmacyOptional.isPresent()) {

            Pharmacy pharmacy = pharmacyOptional.get();

            dto.getPrescription().getMedicines().forEach(medicineDto -> {
                Medicine medicine = medicineRepository.findByUuid(medicineDto.getUuid());
                if (pharmacy.getMedicine().isEmpty()) {
                    throw new NoMedicineFoundException();
                } else if (pharmacy.getMedicine().get(medicine).getStock() < medicineDto.getAmount()) {
                    throw new NoMedicineFoundException();
                } else {
                    prescriptionMedicineAmount.put(medicine, (int) (long) medicineDto.getAmount());
                    int amount = (int) (pharmacy.getMedicine().get(medicine).getStock() - medicineDto.getAmount());
                    pharmacy.getMedicine().get(medicine).setStock(amount);
                    int points = patient.getLoyaltyPoints() + medicine.getPoints();
                    patient.setLoyaltyPoints(points);
                    userRepository.save(patient);
                }
            });

            sendEmail(patient.getEmail(), patient.getName(), pharmacy.getName(), String.valueOf(dto.getBill()));
            pharmacyRepository.save(pharmacy);
            EPrescriptionDto prescriptionDto = dto.getPrescription();
            Calendar prescriptionDate = Calendar.getInstance();
            prescriptionDate.setTime(prescriptionDto.getPrescriptionDate());
            prescriptionRepository.save(new Prescription(prescriptionDto.getId(), patient, prescriptionMedicineAmount, prescriptionDate));

        } else {
            throw new BadRequestException();
        }
    }

    private void sendEmail(String email, String userName, String pharmacyName, String bill) throws MessagingException {
        mailService.sendMail(email, new PatientShoppingEmailParams(userName, pharmacyName, bill), new PatientsShoppingConfirmMailFormatter());
    }
}
