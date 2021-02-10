package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "loyalty_program")
public class LoyaltyProgram {

    public static Long defaultId = 1L;

    @Id
    private Long id;

    @Column
    private int silverMinimumPoints;

    @Column
    private double silverDiscount;

    @Column
    private int goldMinimumPoints;

    @Column
    private double goldDiscount;

    @Column
    private int examPoints;

    @Version
    private Long version;

    public LoyaltyProgram(Long id, int silverMinimumPoints, double silverDiscount, int goldMinimumPoints, double goldDiscount, int examPoints) {
        this.id = id;
        this.silverMinimumPoints = silverMinimumPoints;
        this.silverDiscount = silverDiscount;
        this.goldMinimumPoints = goldMinimumPoints;
        this.goldDiscount = goldDiscount;
        this.examPoints = examPoints;
    }

    public LoyaltyProgram() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public static Long getDefaultId() {
        return defaultId;
    }

    public static void setDefaultId(Long defaultId) {
        LoyaltyProgram.defaultId = defaultId;
    }

    public int getExamPoints() {
        return examPoints;
    }

    public void setExamPoints(int examPoints) {
        this.examPoints = examPoints;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoyaltyProgram that = (LoyaltyProgram) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(silverMinimumPoints, that.silverMinimumPoints) &&
                Objects.equals(silverDiscount, that.silverDiscount) &&
                Objects.equals(goldMinimumPoints, that.goldMinimumPoints) &&
                Objects.equals(goldDiscount, that.goldDiscount) &&
                Objects.equals(examPoints, that.examPoints) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, silverMinimumPoints, silverDiscount, goldMinimumPoints, goldDiscount, examPoints, version);
    }

    @Override
    public String toString() {
        return "LoyaltyProgram{" +
                "id=" + id +
                ", silverMinimumPoints=" + silverMinimumPoints +
                ", silverDiscount=" + silverDiscount +
                ", goldMinimumPoints=" + goldMinimumPoints +
                ", goldDiscount=" + goldDiscount +
                ", examPoints=" + examPoints +
                ", version=" + version +
                '}';
    }

    public double getDiscount(int points) {
        if (points > silverMinimumPoints) {
            if (points > goldMinimumPoints) {
                return goldDiscount;
            }
            return silverDiscount;
        }
        return 1;
    }

}
