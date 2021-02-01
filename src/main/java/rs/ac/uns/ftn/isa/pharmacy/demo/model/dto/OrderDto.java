package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class OrderDto implements Serializable {

    private HashMap<Long, Integer> orderItems;
    private Date deadline;
    private Long id;

    public OrderDto() {

    }

    public OrderDto(HashMap<Long, Integer> orderItems, Date deadline, Long id) {
        this.orderItems = orderItems;
        this.deadline = deadline;
        this.id = id;
    }

    public HashMap<Long, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(HashMap<Long, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderItems=" + orderItems +
                ", deadline=" + deadline +
                ", id=" + id +
                '}';
    }
}
