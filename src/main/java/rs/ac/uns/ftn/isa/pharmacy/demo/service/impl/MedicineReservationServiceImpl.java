package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.MedicineReservationCannotBeCancelledException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MedicineReservationConfirmMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.CreateMedicineReservationParamsDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmaciesMedicinePriceDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LoyaltyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineReservationService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.Constants;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PenaltyPointsConstants;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MedicineReservationServiceImpl implements MedicineReservationService {

    private final MedicineRepository medicineRepository;
    private final MedicineReservationRepository medicineReservationRepository;
    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    private final MailService<MedicineReservationEmailParams> mailService;
    private final LoyaltyService loyaltyService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MedicineReservationServiceImpl(
            MedicineRepository medicineRepository,
            MedicineReservationRepository medicineReservationRepository,
            PharmacyRepository pharmacyRepository,
            UserRepository userRepository,
            MailService<MedicineReservationEmailParams> mailService, LoyaltyService loyaltyService) {
        this.medicineRepository = medicineRepository;
        this.medicineReservationRepository = medicineReservationRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.loyaltyService = loyaltyService;
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
    public boolean isReservationValid(CreateMedicineReservationParamsDto createMedicineReservationParamsDto) {
        try {
            Medicine medicine = getMedicineById(createMedicineReservationParamsDto.getMedicineId());
            Pharmacy pharmacy = getPharmacyById(createMedicineReservationParamsDto.getPharmacyId());
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
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = ObjectOptimisticLockingFailureException.class)
    public void confirmReservation(CreateMedicineReservationParamsDto createMedicineReservationParamsDto, Patient patient) throws MessagingException {
        Medicine medicine = getMedicineById(createMedicineReservationParamsDto.getMedicineId());
        Pharmacy pharmacy = getPharmacyById(createMedicineReservationParamsDto.getPharmacyId());

        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTime(createMedicineReservationParamsDto.getExpirationDate());
        expirationDate.set(Calendar.HOUR, 23);
        expirationDate.set(Calendar.MINUTE, 59);
        expirationDate.set(Calendar.SECOND, 59);

        String uniqueReservationNumber = UUID.randomUUID().toString();
        MedicineReservation medicineReservation = new MedicineReservation(medicine, patient, pharmacy, expirationDate, uniqueReservationNumber);
        medicineReservationRepository.save(medicineReservation);

        MedicineStatus medicineStatus = pharmacy.getMedicine().get(medicine);
        medicineStatus.subtractFromStock(1);
        pharmacy.getMedicine().put(medicine, medicineStatus);
        pharmacyRepository.save(pharmacy);

        patient.addLoyaltyPoints(medicine.getPoints());
        userRepository.save(patient);

        MedicineReservationEmailParams params = new MedicineReservationEmailParams(medicine.getName(),
                createMedicineReservationParamsDto.getExpirationDate(),
                pharmacy.getName(),
                pharmacy.getAddress().getCountry() + ", " + pharmacy.getAddress().getCity() + ", " + pharmacy.getAddress().getStreet(),
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
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = ObjectOptimisticLockingFailureException.class)
    public void cancelMedicineReservation(Long medicineReservationId) throws EntityNotFoundException, MedicineReservationCannotBeCancelledException {
        MedicineReservation medicineReservation = getMedicineReservationById(medicineReservationId);
        if (!isMedicineReservationCancellable(medicineReservation.getExpirationDate())) {
            throw new MedicineReservationCannotBeCancelledException();
        }
        medicineReservationRepository.delete(medicineReservation);

        Pharmacy pharmacy = getPharmacyById(medicineReservation.getPharmacy().getId());
        Medicine medicine = medicineReservation.getMedicine();
        MedicineStatus medicineStatus = pharmacy.getMedicine().get(medicine);

        medicineStatus.addToStock(1);
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

    public ArrayList<PharmaciesMedicinePriceDto> getPharmaciesMedicinePriceDtos(Long medicineId) {
        Medicine medicine = getMedicineById(medicineId);
        ArrayList<PharmaciesMedicinePriceDto> result = new ArrayList<>();
        getPharmaciesWithMedicineOnStock(medicineId).forEach(pharmacy -> {
            result.add(new PharmaciesMedicinePriceDto(
                    pharmacy.getId(), pharmacy.getName(),
                    pharmacy.getAddress().toString(),
                    pharmacy.getAbout(),
                    loyaltyService.getDiscount() * pharmacy.getCurrentMedicinePrice(medicine)));
        });
        return result;
    }
}