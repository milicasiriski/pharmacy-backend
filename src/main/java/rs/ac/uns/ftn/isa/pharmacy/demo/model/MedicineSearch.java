package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "medicine_search")
public class MedicineSearch {

    @Id
    @SequenceGenerator(name = "medicine_search_sequence_generator", sequenceName = "medicine_purchase_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_purchase_sequence_generator")
    private Long id;

    @Column(name = "medicine_id")
    private Long medicineId;

    @Column(name = "search_date")
    private Calendar searchDate;

    @Column(name = "pharmacy_id")
    private Double price;

    public MedicineSearch() {

    }

    public MedicineSearch(Long medicineId, Calendar searchDate, Double price) {
        this.medicineId = medicineId;
        this.searchDate = searchDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Calendar getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Calendar searchDate) {
        this.searchDate = searchDate;
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
        MedicineSearch that = (MedicineSearch) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(medicineId, that.medicineId) &&
                Objects.equals(searchDate, that.searchDate) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicineId, searchDate, price);
    }
}
