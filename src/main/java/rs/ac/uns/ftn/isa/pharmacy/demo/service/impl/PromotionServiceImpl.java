package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.PromotionMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PromotionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PromotionRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PromotionService;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PatientRepository patientRepository;
    private final MailService<PromotionDto> mailService;
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    PromotionServiceImpl(PromotionRepository promotionRepository, PatientRepository patientRepository, Environment env,
                         JavaMailSender javaMailSender, PharmacyRepository pharmacyRepository) {
        this.promotionRepository = promotionRepository;
        this.patientRepository = patientRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.mailService = new MailService<>(env, javaMailSender);
    }

    @Override
    public void addNewPromotion(PromotionDto promotionDto) throws MessagingException {
        PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Promotion promotion = new Promotion(getPromotionInterval(promotionDto), promotionDto.getNotificationMessage(),
                (admin.getPharmacy()));
        promotionRepository.save(promotion);

        // TODO: Send mail to all relevant patients.
        mailService.sendMail(admin.getEmail(), promotionDto, new PromotionMailFormatter());
    }

    @Override
    public boolean updateSubscription(Long pharmacyId) {
        Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Patient> subscribers = patientRepository.findSubscribersByPharmacyId(pharmacyId);
        boolean subscribed;
        if (isSubscribed(pharmacyId)) {
            subscribers = unsubscribe(subscribers, patient);
            subscribed = false;
        } else {
            subscribers.add(patient);
            subscribed = true;
        }
        Optional<Pharmacy> p = pharmacyRepository.findById(pharmacyId);
        if (p.isPresent()) {
            Pharmacy pharmacy = p.get();
            pharmacy.setSubscribers(subscribers);
            pharmacyRepository.save(pharmacy);
            return subscribed;
        } else throw new BadRequestException();
    }

    private List<Patient> unsubscribe(List<Patient> subscribers, Patient patient) {
        List<Patient> newSubscribers = new ArrayList<>();
        subscribers.forEach(s -> {
            if (!s.getId().equals(patient.getId())) {
                newSubscribers.add(s);
            }
        });
        return newSubscribers;
    }

    @Override
    public boolean isSubscribed(Long pharmacyId) {
        List<Long> ids = patientRepository.findSubscribersIdsByPharmacyId(pharmacyId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ids.contains(user.getId());
    }

    public TimeInterval getPromotionInterval(PromotionDto promotionDto) {
        Calendar from = Calendar.getInstance();
        from.setTime(promotionDto.getFrom());

        Calendar to = Calendar.getInstance();
        to.setTime(promotionDto.getTo());

        return new TimeInterval(from, to);
    }
}
