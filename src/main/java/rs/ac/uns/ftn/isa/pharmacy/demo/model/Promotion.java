package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @SequenceGenerator(name = "promotion_sequence_generator", sequenceName = "promotion_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_sequence_generator")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "time_start")),
            @AttributeOverride(name = "end", column = @Column(name = "time_end"))
    })
    TimeInterval periodOfValidity;

    @Column(name = "promo_message")
    String promotionNotificationMessage;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    public Promotion() {

    }

    public Promotion(TimeInterval periodOfValidity, String promotionNotificationMessage, Pharmacy pharmacy) {
        this.periodOfValidity = periodOfValidity;
        this.promotionNotificationMessage = promotionNotificationMessage;
        this.pharmacy = pharmacy;
    }


    public TimeInterval getPeriodOfValidity() {
        return periodOfValidity;
    }

    public void setPeriodOfValidity(TimeInterval periodOfValidity) {
        this.periodOfValidity = periodOfValidity;
    }

    public String getPromotionNotificationMessage() {
        return promotionNotificationMessage;
    }

    public void setPromotionNotificationMessage(String promotionNotification) {
        this.promotionNotificationMessage = promotionNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(periodOfValidity, promotion.periodOfValidity) &&
                Objects.equals(promotionNotificationMessage, promotion.promotionNotificationMessage) &&
                Objects.equals(id, promotion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodOfValidity, promotionNotificationMessage, id);
    }
}
