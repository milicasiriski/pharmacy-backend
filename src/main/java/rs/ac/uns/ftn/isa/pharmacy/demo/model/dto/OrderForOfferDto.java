package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderForOfferDto implements Serializable {

    private Map<MedicineDto, Integer> orderItems;
    private Date deadline;
    private Long id;

    public OrderForOfferDto(Map<MedicineDto, Integer> orderItems, Date deadline, Long id) {
        this.orderItems = orderItems;
        this.deadline = deadline;
        this.id = id;
    }

    public OrderForOfferDto() {
    }

    public Map<MedicineDto, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(HashMap<MedicineDto, Integer> orderItems) {
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
}
