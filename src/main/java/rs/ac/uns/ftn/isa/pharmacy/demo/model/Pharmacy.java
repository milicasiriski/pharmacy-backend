package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    @Id
    @SequenceGenerator(name = "pharmacy_sequence_generator", sequenceName = "pharmacy_sequence", initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_sequence_generator")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "about")
    private String about;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pharmacy_id")
    private List<Dermatologist> dermatologists;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pharmacy_id")
    private List<Pharmacist> pharmacists;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pharmacy_id")
    private List<Medicine> medicine;

    @ElementCollection
    @CollectionTable(name = "pharmacy_medicine_price_list_mapping", joinColumns = @JoinColumn(name = "pharmacy_id"))
    @MapKeyJoinColumn(name = "medicine_id", referencedColumnName = "id")
    @Column(name = "price")
    private Map<Medicine, Double> medicinePriceList;

    @ElementCollection
    @CollectionTable(name = "pharmacy_exam_price_list_mapping", joinColumns = @JoinColumn(name = "pharmacy_id"))
    @MapKeyJoinColumn(name = "exam_id", referencedColumnName = "id")
    @Column(name = "price")
    private Map<Exam, Double> examinePriceList;

    public Pharmacy() {

    }

    public Pharmacy(String name, String address, String about, List<Dermatologist> dermatologists, List<Pharmacist> pharmacists, List<Medicine> medicine, HashMap<Medicine, Double> medicinePriceList, HashMap<Exam, Double> examinePriceList) {
        this.name = name;
        this.address = address;
        this.about = about;
        this.dermatologists = dermatologists;
        this.pharmacists = pharmacists;
        this.medicine = medicine;
        this.medicinePriceList = medicinePriceList;
        this.examinePriceList = examinePriceList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(List<Dermatologist> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public List<Pharmacist> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(List<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }

    public Map<Medicine, Double> getMedicinePriceList() {
        return medicinePriceList;
    }

    public void setMedicinePriceList(HashMap<Medicine, Double> medicinePriceList) {
        this.medicinePriceList = medicinePriceList;
    }

    public Map<Exam, Double> getExaminePriceList() {
        return examinePriceList;
    }

    public void setExaminePriceList(HashMap<Exam, Double> examinePriceList) {
        this.examinePriceList = examinePriceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return name.equals(pharmacy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", about='" + about + '\'' +
//                ", dermatologists=" + dermatologists +
//                ", pharmacists=" + pharmacists +
//                ", medicine=" + medicine +
//                ", medicinePriceList=" + medicinePriceList +
//                ", examinePriceList=" + examinePriceList +
                '}';
    }
}
