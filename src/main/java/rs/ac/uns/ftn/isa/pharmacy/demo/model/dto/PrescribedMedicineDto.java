package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class PrescribedMedicineDto implements Serializable {
    private String uuid;
    private String name;
    private Long amount;

    public PrescribedMedicineDto(String uuid, String name, Long amount) {
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
    }

    public PrescribedMedicineDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PrescribedMedicineDto{" +
                "\"uuid\"=\"" + uuid + '\"' +
                ", \"name\"=\"" + name + '\"' +
                ", \"amount\"=" + amount +
                '}';
    }
}
