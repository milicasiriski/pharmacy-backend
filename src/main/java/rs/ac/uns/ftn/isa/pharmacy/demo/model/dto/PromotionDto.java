package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PromotionDto implements Serializable {

    private String notificationMessage;
    private Date from;
    private Date to;

    public PromotionDto() {

    }

    public PromotionDto(String notificationMessage, Date from, Date to) {
        this.notificationMessage = notificationMessage;
        this.from = from;
        this.to = to;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionDto that = (PromotionDto) o;
        return Objects.equals(notificationMessage, that.notificationMessage) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationMessage, from, to);
    }

    @Override
    public String toString() {
        return "PromotionDto{" +
                "notificationMessage='" + notificationMessage + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
