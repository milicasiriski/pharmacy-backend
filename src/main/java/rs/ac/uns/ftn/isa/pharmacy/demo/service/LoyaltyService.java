package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LoyaltyProgramDto;

public interface LoyaltyService {
    void update(LoyaltyProgramDto program);

    double getDiscount();

    void givePointsForExam();

    LoyaltyProgramDto getLoyaltyProgramDto();
}
