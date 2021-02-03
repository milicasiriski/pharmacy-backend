package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.OfferStatus;

import java.util.Date;

public class OfferDto {

    private Long shippingDays;
    private Long price;
    private Long orderId;
    private OfferStatus status;
    private Long offerId;
    private Date orderDeadline;

    public OfferDto(Long shippingDays, Long price, Long orderId, OfferStatus status, Long offerId, Date orderDeadline) {
        this.shippingDays = shippingDays;
        this.price = price;
        this.orderId = orderId;
        this.status = status;
        this.offerId = offerId;
        this.orderDeadline = orderDeadline;
    }

    public OfferDto() {
    }

    public Long getShippingDays() {
        return shippingDays;
    }

    public void setShippingDays(Long shippingDays) {
        this.shippingDays = shippingDays;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Date getOrderDeadline() {
        return orderDeadline;
    }

    public void setOrderDeadline(Date orderDeadline) {
        this.orderDeadline = orderDeadline;
    }

    @Override
    public String toString() {
        return "OfferDto{" +
                "shippingDays=" + shippingDays +
                ", price=" + price +
                ", orderId=" + orderId +
                ", status=" + status +
                ", offerId=" + offerId +
                ", orderDeadline=" + orderDeadline +
                '}';
    }
}
