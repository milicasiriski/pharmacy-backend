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
    @SequenceGenerator(name = "medicine_sequence_generator", sequenceName = "medicine_sequence", initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_sequence_generator")
    private Long id;

    @Column(name = "name", unique=true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "composition")
    private String composition;

    @Column(name = "form")
    private MedicineForm form;

    @Column(name = "type")
    private MedicineType type;

    @Column(name = "prescribed")
    private boolean prescribed;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medicine_alternatives",
            joinColumns = {@JoinColumn(name = "medicine_id")},
            inverseJoinColumns = {@JoinColumn(name = "alternative_id")})
    private List<Medicine> alternatives;

    public Medicine() {
    }

    public Medicine(String name, String description, String manufacturer, String composition,
                    MedicineForm form, MedicineType type, boolean prescribed, List<Medicine> alternatives) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.composition = composition;
        this.form = form;
        this.type = type;
        this.prescribed = prescribed;
        this.alternatives = alternatives;
    }

    public Medicine(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                id.equals(medicine.id) &&
                name.equals(medicine.name) &&
                Objects.equals(description, medicine.description) &&
                Objects.equals(manufacturer, medicine.manufacturer) &&
                Objects.equals(composition, medicine.composition) &&
                form == medicine.form &&
                type == medicine.type &&
                Objects.equals(alternatives, medicine.alternatives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, manufacturer, composition, form, type, prescribed, alternatives);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", composition='" + composition + '\'' +
                ", form=" + form +
                ", type=" + type +
                ", prescribed=" + prescribed +
                ", alternatives=" + alternatives +
                '}';
    }
}