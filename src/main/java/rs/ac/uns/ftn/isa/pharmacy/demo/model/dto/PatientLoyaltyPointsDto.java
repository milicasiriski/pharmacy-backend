package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.util.LoyaltyProgramStatus;

import java.io.Serializable;

public class PatientLoyaltyPointsDto implements Serializable {
    private LoyaltyProgramStatus status;
    private int points;
    private double discount;

    public PatientLoyaltyPointsDto() {
    }

    public PatientLoyaltyPointsDto(LoyaltyProgramStatus status, int points, double discount) {
        this.status = status;
        this.points = points;
        this.discount = discount;
    }

    public LoyaltyProgramStatus getStatus() {
        return status;
    }

    public void setStatus(LoyaltyProgramStatus status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
