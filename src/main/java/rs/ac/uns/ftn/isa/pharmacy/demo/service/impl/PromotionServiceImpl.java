package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.PromotionMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.PharmacyAdmin;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Promotion;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.TimeInterval;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PromotionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PromotionRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PromotionService;

import javax.mail.MessagingException;
import java.util.Calendar;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final MailService<PromotionDto> mailService;

    @Autowired
    PromotionServiceImpl(PromotionRepository promotionRepository, Environment env,
                         JavaMailSender javaMailSender) {
        this.promotionRepository = promotionRepository;
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

    public TimeInterval getPromotionInterval(PromotionDto promotionDto) {
        Calendar from = Calendar.getInstance();
        from.setTime(promotionDto.getFrom());

        Calendar to = Calendar.getInstance();
        to.setTime(promotionDto.getTo());

        return new TimeInterval(from, to);
    }
}
