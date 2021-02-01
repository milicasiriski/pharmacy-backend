package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineForm;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineType;

import java.io.Serializable;
import java.util.List;

public class MedicineDto implements Serializable {

    private long id;
    private String uuid;
    private String name;
    private String description;
    private String manufacturer;
    private String composition;
    private MedicineForm form;
    private MedicineType type;
    private boolean prescribed;
    private String recommendedDose;
    private String sideEffects;
    private List<MedicineNameUuidDto> alternatives;
    private int points = 0;

    public MedicineDto(long id, String uuid, String name, String description, String manufacturer, String composition, MedicineForm form, MedicineType type, boolean prescribed, String recommendedDose, String sideEffects, List<MedicineNameUuidDto> alternatives, int points) {
        this.id = id;
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
        this.points = points;
    }

    public MedicineDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isPrescribed() {
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

    public List<MedicineNameUuidDto> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<MedicineNameUuidDto> alternatives) {
        this.alternatives = alternatives;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "MedicineDto{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", composition='" + composition + '\'' +
                ", form=" + form +
                ", type=" + type +
                ", prescribed=" + prescribed +
                ", recommendedDose='" + recommendedDose + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", alternatives=" + alternatives +
                ", points=" + points +
                '}';
    }
}
