package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "medicine_status")
public class MedicineStatus {

    @Id
    @SequenceGenerator(name = "medicine_status_sequence_generator", sequenceName = "medicine_status_sequence", initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_status_sequence_generator")
    private Long id;

    @Column(name = "stock")
    private int stock;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_status_id")
    private List<MedicinePriceListItem> prices;

    public MedicineStatus() {

    }

    public MedicineStatus(int stock, List<MedicinePriceListItem> prices) {
        this.stock = stock;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<MedicinePriceListItem> getPrices() {
        return prices;
    }

    public void setPrices(List<MedicinePriceListItem> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineStatus that = (MedicineStatus) o;
        return stock == that.stock &&
                Objects.equals(prices, that.prices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, prices);
    }
}
