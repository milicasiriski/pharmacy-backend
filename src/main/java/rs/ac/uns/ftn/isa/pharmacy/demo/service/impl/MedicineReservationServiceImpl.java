package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.MedicineReservationCannotBeCancelledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MedicineReservationConfirmMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.CreateMedicineReservationParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.Constants;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PenaltyPointsConstants;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MedicineReservationServiceImpl implements MedicineReservationService {
    private MedicineRepository medicineRepository;
    private MedicineReservationRepository medicineReservationRepository;
    private PharmacyRepository pharmacyRepository;
    private UserRepository userRepository;
    private MailService<MedicineReservationEmailParams> mailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MedicineReservationServiceImpl(
            MedicineRepository medicineRepository,
            MedicineReservationRepository medicineReservationRepository,
            PharmacyRepository pharmacyRepository,
            UserRepository userRepository,
            MailService<MedicineReservationEmailParams> mailService) {
        this.medicineRepository = medicineRepository;
        this.medicineReservationRepository = medicineReservationRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
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
    public boolean isReservationValid(CreateMedicineReservationParams createMedicineReservationParams) {
        try {
            Medicine medicine = getMedicineById(createMedicineReservationParams.getMedicineId());
            Pharmacy pharmacy = getPharmacyById(createMedicineReservationParams.getPharmacyId());
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
    public void confirmReservation(CreateMedicineReservationParams createMedicineReservationParams, Patient patient) throws MessagingException {
        Medicine medicine = getMedicineById(createMedicineReservationParams.getMedicineId());
        Pharmacy pharmacy = getPharmacyById(createMedicineReservationParams.getPharmacyId());

        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTime(createMedicineReservationParams.getExpirationDate());
        expirationDate.set(Calendar.HOUR, 23);
        expirationDate.set(Calendar.MINUTE, 59);
        expirationDate.set(Calendar.SECOND, 59);

        String uniqueReservationNumber = UUID.randomUUID().toString();
        MedicineReservation medicineReservation = new MedicineReservation(medicine, patient, pharmacy, expirationDate, uniqueReservationNumber);
        medicineReservationRepository.save(medicineReservation);

        MedicineStatus medicineStatus = pharmacy.getMedicine().get(medicine);
        int currentStock = medicineStatus.getStock();
        medicineStatus.setStock(currentStock - 1);
        pharmacy.getMedicine().put(medicine, medicineStatus);
        pharmacyRepository.save(pharmacy);

        // TODO: Give real address to constructor
        MedicineReservationEmailParams params = new MedicineReservationEmailParams(medicine.getName(),
                createMedicineReservationParams.getExpirationDate(),
                pharmacy.getName(),
                "",
                uniqueReservationNumber);
        mailService.sendMail(patient.getEmail(), params, new MedicineReservationConfirmMailFormatter());
    }

    @Override
    public Medicine getMedicineById(Long id) throws EntityNotFoundException {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()) {
            return optionalMedicine.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Iterable<MedicineReservation> getAllMedicineReservationsForPatient(Patient patient) {
        return medicineReservationRepository.findByPatient(patient);
    }

    @Override
    public boolean isMedicineReservationCancellable(Calendar deadline) {
        Calendar now = Calendar.getInstance();
        long differenceInMilliseconds = deadline.getTime().getTime() - now.getTime().getTime();
        long differenceInHours = TimeUnit.HOURS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);

        return differenceInHours >= Constants.MEDICINE_RESERVATION_CANCELLATION_HOURS;
    }

    @Override
    public void cancelMedicineReservation(Long medicineReservationId) throws EntityNotFoundException, MedicineReservationCannotBeCancelledException {
        MedicineReservation medicineReservation = getMedicineReservationById(medicineReservationId);
        if (!isMedicineReservationCancellable(medicineReservation.getExpirationDate())) {
            throw new MedicineReservationCannotBeCancelledException();
        }
        medicineReservationRepository.delete(medicineReservation);

        Pharmacy pharmacy = getPharmacyById(medicineReservation.getPharmacy().getId());
        Medicine medicine = medicineReservation.getMedicine();
        MedicineStatus medicineStatus = pharmacy.getMedicine().get(medicine);

        int currentStock = medicineStatus.getStock();
        medicineStatus.setStock(currentStock + 1);
        pharmacy.getMedicine().put(medicine, medicineStatus);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void removeAllExpiredMedicineReservations() {
        Iterable<MedicineReservation> reservationsToDelete = getExpiredMedicineReservations();
        reservationsToDelete.forEach(it -> {
            givePatientPenaltyPoints(it.getPatient());
            deleteMedicineReservation(it);
        });
    }

    private Iterable<MedicineReservation> getExpiredMedicineReservations() {
        List<MedicineReservation> result = new ArrayList<>();
        medicineReservationRepository.findAll().forEach(it -> {
            if (isMedicineReservationExpired(it)) {
                result.add(it);
            }
        });
        return result;
    }

    private boolean isMedicineReservationExpired(MedicineReservation medicineReservation) {
        Calendar now = Calendar.getInstance();
        return medicineReservation.getExpirationDate().compareTo(now) <= 0;
    }

    private void givePatientPenaltyPoints(Patient patient) {
        patient.addPenaltyPoints(PenaltyPointsConstants.MEDICINE_RESERVATION_EXPIRED_PENALTY);
        userRepository.save(patient);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        logger.info("{} Patient with id {} received penalty points.", timestamp, patient.getId());
    }

    private void deleteMedicineReservation(MedicineReservation medicineReservation) {
        medicineReservationRepository.delete(medicineReservation);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        logger.info("{} Deleted medicine reservation with id: {}", timestamp, medicineReservation.getId());
    }

    private Pharmacy getPharmacyById(Long id) throws EntityNotFoundException {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(id);
        if (optionalPharmacy.isPresent()) {
            return optionalPharmacy.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private MedicineReservation getMedicineReservationById(Long id) throws EntityNotFoundException {
        Optional<MedicineReservation> optionalMedicineReservation = medicineReservationRepository.findById(id);
        if (optionalMedicineReservation.isPresent()) {
            return optionalMedicineReservation.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}