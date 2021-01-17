package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class OrderDto implements Serializable {
    private HashMap<String, Integer> orderItems;
    private Date deadline;

    public OrderDto() {

    }

    public HashMap<String, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(HashMap<String, Integer> orderItems) {
        this.orderItems = orderItems;
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
        return orderItems.equals(orderDto.orderItems) &&
                deadline.equals(orderDto.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItems, deadline);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderItems=" + orderItems +
                ", deadline=" + deadline +
                '}';
    }
}
