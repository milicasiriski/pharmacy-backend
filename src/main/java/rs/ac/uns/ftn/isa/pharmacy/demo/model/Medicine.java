package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineForm;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

    @Id
    @SequenceGenerator(name = "medicine_sequence_generator", sequenceName = "medicine_sequence", initialValue = 6)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_sequence_generator")
    private Long id;

    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "composition")
    private String composition;

    @Column(name = "ratings")
    private Double ratings;

    @Column(name = "form")
    private MedicineForm form;

    @Column(name = "type")
    private MedicineType type;

    @Column(name = "prescribed")
    private boolean prescribed;

    @Column(name = "recommended_dose")
    private String recommendedDose;

    @Column(name = "side_effects")
    private String sideEffects;

    @Column(name = "points")
    private int points = 0;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medicine_alternatives",
            joinColumns = {@JoinColumn(name = "medicine_id")},
            inverseJoinColumns = {@JoinColumn(name = "alternative_id")})
    private List<Medicine> alternatives;

    public Medicine() {
    }

    public Medicine(String name) {
        this.name = name;
    }

    public Medicine(long id, String uuid, String name, String description, String manufacturer, String composition, Double ratings, MedicineForm form, MedicineType type, boolean prescribed, String recommendedDose, String sideEffects, int points, List<Medicine> alternatives) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.composition = composition;
        this.ratings = ratings;
        this.form = form;
        this.type = type;
        this.prescribed = prescribed;
        this.recommendedDose = recommendedDose;
        this.sideEffects = sideEffects;
        this.points = points;
        this.alternatives = alternatives;
    }

    public Medicine(String uuid, String name, String description, String manufacturer, String composition, Double ratings, MedicineForm form, MedicineType type, boolean prescribed, String recommendedDose, String sideEffects, int points, List<Medicine> alternatives) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.composition = composition;
        this.ratings = ratings;
        this.form = form;
        this.type = type;
        this.prescribed = prescribed;
        this.recommendedDose = recommendedDose;
        this.sideEffects = sideEffects;
        this.points = points;
        this.alternatives = alternatives;
    }

    public Medicine(String uuid, String name, String description, String manufacturer, String composition, MedicineForm form, MedicineType type, boolean prescribed, String recommendedDose, String sideEffects, int points) {
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
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Medicine> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Medicine> alternatives) {
        this.alternatives = alternatives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return prescribed == medicine.prescribed &&
                points == medicine.points &&
                Objects.equals(id, medicine.id) &&
                Objects.equals(uuid, medicine.uuid) &&
                Objects.equals(name, medicine.name) &&
                Objects.equals(description, medicine.description) &&
                Objects.equals(manufacturer, medicine.manufacturer) &&
                Objects.equals(composition, medicine.composition) &&
                Objects.equals(ratings, medicine.ratings) &&
                form == medicine.form &&
                type == medicine.type &&
                Objects.equals(recommendedDose, medicine.recommendedDose) &&
                Objects.equals(sideEffects, medicine.sideEffects) &&
                Objects.equals(alternatives, medicine.alternatives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, name, description, manufacturer, composition, ratings, form, type, prescribed, recommendedDose, sideEffects, points, alternatives);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", composition='" + composition + '\'' +
                ", ratings=" + ratings +
                ", form=" + form +
                ", type=" + type +
                ", prescribed=" + prescribed +
                ", recommendedDose='" + recommendedDose + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", points=" + points +
                ", alternatives=" + alternatives +
                '}';
    }
}