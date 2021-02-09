package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PromotionDto;

import javax.mail.MessagingException;
import java.util.List;

public interface PromotionService {
    void addNewPromotion(PromotionDto promotionDto) throws MessagingException;

    boolean updateSubscription(Long pharmacyId);

    boolean isSubscribed(Long pharmacyId);

    List<PharmacyDto> getSubscribedPharmacies();
}
