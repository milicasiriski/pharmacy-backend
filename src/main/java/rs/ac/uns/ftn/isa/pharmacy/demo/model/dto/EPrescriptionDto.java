package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EPrescriptionDto implements Serializable {
    private String id;
    private String name;
    private List<PrescribedMedicineDto> medicines;
    private Date prescriptionDate;

    public EPrescriptionDto(String id, String name, List<PrescribedMedicineDto> medicines, Date prescriptionDate) {
        this.id = id;
        this.name = name;
        this.medicines = medicines;
        this.prescriptionDate = prescriptionDate;
    }

    public EPrescriptionDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PrescribedMedicineDto> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescribedMedicineDto> medicines) {
        this.medicines = medicines;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    @Override
    public String toString() {
        return "EPrescriptionDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", medicines=" + medicines +
                ", prescriptionDate=" + prescriptionDate +
                '}';
    }
}
