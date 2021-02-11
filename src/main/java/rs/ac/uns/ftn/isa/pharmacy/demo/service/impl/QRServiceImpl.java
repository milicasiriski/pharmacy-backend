package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PrescriptionUsedException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.PatientsShoppingConfirmMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.EPrescriptionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientShoppingEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PrescribedMedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.QRResultDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.AuthenticationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LoyaltyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.QRService;

import javax.mail.MessagingException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional(readOnly = true)
public class QRServiceImpl implements QRService {

    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MailService<PatientShoppingEmailParams> mailService;
    private final MedicinePurchaseRepository medicinePurchaseRepository;
    private final LoyaltyService loyaltyService;
    private final AuthenticationService authenticationService;

    public QRServiceImpl(PharmacyRepository pharmacyRepository, MedicineRepository medicineRepository, UserRepository userRepository, PrescriptionRepository prescriptionRepository, MailService<PatientShoppingEmailParams> mailService, MedicinePurchaseRepository medicinePurchaseRepository, LoyaltyService loyaltyService, AuthenticationService authenticationService) {
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.mailService = mailService;
        this.medicinePurchaseRepository = medicinePurchaseRepository;
        this.loyaltyService = loyaltyService;
        this.authenticationService = authenticationService;
    }

    @Override
    public List<QRResultDto> findPharmacies(EPrescriptionDto dto) {
        Patient patient = (Patient) authenticationService.getLoggedUser();
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
                QRResultDto resultDto = new QRResultDto((int) (bill * loyaltyService.getDiscount()), pharmacy.getAddress().getStreet(), pharmacy.getName(), dto, pharmacy.getId(), pharmacy.getRating());
                qrResultDtos.add(resultDto);
            }
        });
        if (qrResultDtos.isEmpty()) {
            throw new NoMedicineFoundException();
        }
        return qrResultDtos;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void buy(QRResultDto dto) throws Exception {
        if (prescriptionRepository.findById(dto.getPrescription().getId()).isPresent()) {
            throw new PrescriptionUsedException();
        }
        Patient patient = (Patient) authenticationService.getLoggedUser();
        Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(dto.getPharmacyId());
        Map<Medicine, Integer> prescriptionMedicineAmount = new HashMap<>();
        AtomicInteger price = new AtomicInteger();

        if (pharmacyOptional.isPresent()) {

            Pharmacy pharmacy = pharmacyOptional.get();

            dto.getPrescription().getMedicines().forEach(medicineDto -> {
                Medicine medicine = medicineRepository.findByUuid(medicineDto.getUuid());
                if (pharmacy.getMedicine().isEmpty()) {
                    throw new NoMedicineFoundException();
                } else if (pharmacy.getMedicine().get(medicine).getStock() < medicineDto.getAmount()) {
                    throw new NoMedicineFoundException();
                } else {
                    price.addAndGet((int) (medicineDto.getAmount() * pharmacy.getCurrentMedicinePrice(medicine) * loyaltyService.getDiscount()));
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
            medicinePurchaseRepository.save(new MedicinePurchase(prescriptionMedicineAmount, pharmacy, patient, price.longValue()));
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
