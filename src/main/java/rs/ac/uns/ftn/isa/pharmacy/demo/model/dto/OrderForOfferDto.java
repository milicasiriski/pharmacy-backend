package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderForOfferDto implements Serializable {

    private List<MedicineAmountForOfferDto> orderItems;
    private Date deadline;
    private Long id;

    public OrderForOfferDto(List<MedicineAmountForOfferDto> orderItems, Date deadline, Long id) {
        this.orderItems = orderItems;
        this.deadline = deadline;
        this.id = id;
    }

    public OrderForOfferDto() {
    }

    public List<MedicineAmountForOfferDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<MedicineAmountForOfferDto> orderItems) {
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
