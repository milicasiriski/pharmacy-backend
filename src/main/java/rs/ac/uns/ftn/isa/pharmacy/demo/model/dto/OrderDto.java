package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrderDto implements Serializable {
    private String name;
    private int amount;
    private Date deadline;

    public OrderDto() {

    }

    public OrderDto(String name, int amount, Date deadline) {
        this.name = name;
        this.amount = amount;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
        OrderDto orderDto = (OrderDto) o;
        return amount == orderDto.amount &&
                name.equals(orderDto.name) &&
                deadline.equals(orderDto.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, deadline);
    }
}
