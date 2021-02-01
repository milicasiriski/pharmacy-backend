package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.OfferStatus;

public class OfferDto {

    private Long shippingDays;
    private Long price;
    private OrderDto order;
    private OfferStatus status;

    public OfferDto(Long shippingDays, Long price, OrderDto order, OfferStatus status) {
        this.shippingDays = shippingDays;
        this.price = price;
        this.order = order;
        this.status = status;
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

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OfferDto{" +
                "shippingDays=" + shippingDays +
                ", price=" + price +
                ", orderId=" + order +
                ", status=" + status +
                '}';
    }
}
