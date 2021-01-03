package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import java.util.Date;
import java.util.Objects;

public class Order {
    private Medicine medicine;
    private Integer amount;
    private Date deadline;

    public Order(Medicine medicine, Integer amount, Date deadline) {
        this.medicine = medicine;
        this.amount = amount;
        this.deadline = deadline;
    }

    public Order() {

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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return medicine.equals(order.medicine) &&
                amount.equals(order.amount) &&
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
