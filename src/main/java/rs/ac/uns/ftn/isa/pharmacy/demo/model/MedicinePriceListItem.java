package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medicine_price_list_item")
public class MedicinePriceListItem {

    @Id
    @SequenceGenerator(name = "medicine_price_list_item_sequence_generator", sequenceName = "medicine_price_list_item_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_price_list_item_sequence_generator")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "time_start")),
            @AttributeOverride(name = "end", column = @Column(name = "time_end"))
    })
    private TimeInterval timeInterval;

    @Column(name = "price")
    private Double price;

    public MedicinePriceListItem() {

    }

    public MedicinePriceListItem(Long id, TimeInterval timeInterval, Double price) {
        this.id = id;
        this.timeInterval = timeInterval;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeInterval getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(TimeInterval timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicinePriceListItem that = (MedicinePriceListItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timeInterval, that.timeInterval) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeInterval, price);
    }
}
