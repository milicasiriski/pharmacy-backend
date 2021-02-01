package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.OfferStatus;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_offer")
public class Offer implements Serializable {

    @Id
    @SequenceGenerator(name = "offer_sequence_generator", sequenceName = "offer_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_sequence_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "price")
    private Long price;

    @Column(name = "delivery-date")
    private Long shippingDays;

    @Column(name = "status")
    private OfferStatus status;

    public Offer(Order order, Long price, Long shippingDays, OfferStatus status) {
        this.order = order;
        this.price = price;
        this.shippingDays = shippingDays;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getShippingDays() {
        return shippingDays;
    }

    public void setShippingDays(Long deliveryDate) {
        this.shippingDays = deliveryDate;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(id, offer.id) &&
                Objects.equals(order, offer.order) &&
                Objects.equals(price, offer.price) &&
                Objects.equals(shippingDays, offer.shippingDays) &&
                status == offer.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, price, shippingDays, status);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", order=" + order +
                ", price=" + price +
                ", deliveryDate=" + shippingDays +
                ", status=" + status +
                '}';
    }
}
