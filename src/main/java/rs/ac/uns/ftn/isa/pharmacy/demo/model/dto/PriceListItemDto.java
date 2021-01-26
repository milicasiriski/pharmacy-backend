package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class PriceListItemDto implements Serializable {

    private Long medicineId;
    private Double price;
    private Date from;
    private Date to;

    public PriceListItemDto() {

    }

    public PriceListItemDto(Long medicineId, Double price, Date from, Date to) {
        this.medicineId = medicineId;
        this.price = price;
        this.from = from;
        this.to = to;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }
}
