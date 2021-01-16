package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "medicine_order")
public class Order implements Serializable {

    @Id
    @SequenceGenerator(name = "order_sequence_generator", sequenceName = "order_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Column(name = "amount")
    private int amount;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "deadline")
    private Calendar deadline;

    public Order(Medicine medicine, Integer amount, Calendar deadline) {
        this.medicine = medicine;
        this.amount = amount;
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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
        return medicine.equals(order.medicine) &&
                amount == order.amount &&
                deadline.equals(order.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicine, amount, deadline);
    }

    @Override
    public String toString() {
        return "Order{" +
                "medicine=" + medicine +
                ", amount=" + amount +
                ", deadline=" + deadline +
                '}';
    }
}
