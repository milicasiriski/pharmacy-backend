package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "medicine_order")
public class Order implements Serializable {

    @Id
    @SequenceGenerator(name = "order_sequence_generator", sequenceName = "order_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence_generator")
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_medicine_mapping", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "medicine_id", referencedColumnName = "id")
    @Column(name = "order_medicine_amount")
    private Map<Medicine, Integer> medicineAmount;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "deadline")
    private Calendar deadline;

    public Order(Map<Medicine, Integer> medicineAmount, Calendar deadline) {
        this.medicineAmount = medicineAmount;
        this.deadline = deadline;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Medicine, Integer> getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(Map<Medicine, Integer> medicineAmount) {
        this.medicineAmount = medicineAmount;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                medicineAmount.equals(order.medicineAmount) &&
                deadline.equals(order.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicineAmount, deadline);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", medicineAmount=" + medicineAmount +
                ", deadline=" + deadline +
                '}';
    }
}
