package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class PromotionDto implements Serializable {

    private String notificationMessage;
    private Date from;
    private Date to;
    private Double discount;

    public PromotionDto() {

    }

    public PromotionDto(String notificationMessage, Date from, Date to, Double discount) {
        this.notificationMessage = notificationMessage;
        this.from = from;
        this.to = to;
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
