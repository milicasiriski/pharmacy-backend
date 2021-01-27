package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import net.bytebuddy.asm.Advice;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineForm;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineType;

import java.util.List;
import java.util.Objects;

public class MedicineDto {

    private String uuid;
    private String name;
    private String description;
    private String manufacturer;
    private String composition;
    private MedicineForm form;
    private MedicineType type;
    private Boolean prescribed;
    private String recommendedDose;
    private String sideEffects;
    private List<String> alternatives;

    public MedicineDto(String uuid, String name, String description, String manufacturer, String composition, MedicineForm form, MedicineType type, boolean prescribed, String recommendedDose, String sideEffects, List<String> alternatives) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.composition = composition;
        this.form = form;
        this.type = type;
        this.prescribed = prescribed;
        this.recommendedDose = recommendedDose;
        this.sideEffects = sideEffects;
        this.alternatives = alternatives;
    }

    public MedicineDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public MedicineForm getForm() {
        return form;
    }

    public void setForm(MedicineForm form) {
        this.form = form;
    }

    public MedicineType getType() {
        return type;
    }

    public void setType(MedicineType type) {
        this.type = type;
    }

    public boolean getPrescribed() {
        return prescribed;
    }

    public void setPrescribed(boolean prescribed) {
        this.prescribed = prescribed;
    }

    public String getRecommendedDose() {
        return recommendedDose;
    }

    public void setRecommendedDose(String recommendedDose) {
        this.recommendedDose = recommendedDose;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    @Override
    public String toString() {
        return "MedicineDto{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", composition='" + composition + '\'' +
                ", form='" + form + '\'' +
                ", type='" + type + '\'' +
                ", prescribed='" + prescribed + '\'' +
                ", recommendedDose='" + recommendedDose + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", alternatives=" + alternatives +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineDto that = (MedicineDto) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(composition, that.composition) &&
                Objects.equals(form, that.form) &&
                Objects.equals(type, that.type) &&
                Objects.equals(prescribed, that.prescribed) &&
                Objects.equals(recommendedDose, that.recommendedDose) &&
                Objects.equals(sideEffects, that.sideEffects) &&
                Objects.equals(alternatives, that.alternatives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, description, manufacturer, composition, form, type, prescribed, recommendedDose, sideEffects, alternatives);
    }
}
