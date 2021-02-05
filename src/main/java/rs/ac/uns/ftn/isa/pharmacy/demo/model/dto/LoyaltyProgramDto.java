package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;


public class LoyaltyProgramDto {

    private int silverMinimumPoints;
    private double silverDiscount;
    private int goldMinimumPoints;
    private double goldDiscount;
    private int examPoints;

    public LoyaltyProgramDto(int silverMinimumPoints, double silverDiscount, int goldMinimumPoints, double goldDiscount, int examPoints) {
        this.silverMinimumPoints = silverMinimumPoints;
        this.silverDiscount = silverDiscount;
        this.goldMinimumPoints = goldMinimumPoints;
        this.goldDiscount = goldDiscount;
        this.examPoints = examPoints;
    }

    public LoyaltyProgramDto() {
    }

    public int getSilverMinimumPoints() {
        return silverMinimumPoints;
    }

    public void setSilverMinimumPoints(int silverMinimumPoints) {
        this.silverMinimumPoints = silverMinimumPoints;
    }

    public double getSilverDiscount() {
        return silverDiscount;
    }

    public void setSilverDiscount(double silverDiscount) {
        this.silverDiscount = silverDiscount;
    }

    public int getGoldMinimumPoints() {
        return goldMinimumPoints;
    }

    public void setGoldMinimumPoints(int goldMinimumPoints) {
        this.goldMinimumPoints = goldMinimumPoints;
    }

    public double getGoldDiscount() {
        return goldDiscount;
    }

    public void setGoldDiscount(double goldDiscount) {
        this.goldDiscount = goldDiscount;
    }

    public int getExamPoints() {
        return examPoints;
    }

    public void setExamPoints(int examPoints) {
        this.examPoints = examPoints;
    }
}
