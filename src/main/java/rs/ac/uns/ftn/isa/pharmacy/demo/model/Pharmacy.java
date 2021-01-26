package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "pharmacy")
public class Pharmacy implements Serializable {

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pharmacy_dermatologist",
            joinColumns = {@JoinColumn(name = "pharmacy_id")},
            inverseJoinColumns = {@JoinColumn(name = "dermatologist_id")})
    private List<Dermatologist> dermatologists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pharmacy_pharmacists",
            joinColumns = {@JoinColumn(name = "pharmacy_id")},
            inverseJoinColumns = {@JoinColumn(name = "pharmacist_id")})
    private List<Pharmacist> pharmacists;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacy_id")
    private List<PharmacyAdmin> pharmacyAdmins;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicine_status_mapping",
            joinColumns = {@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_status_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "medicine_id")
    private Map<Medicine, MedicineStatus> medicine;

    @ElementCollection
    @CollectionTable(name = "pharmacy_exam_price_list_mapping", joinColumns = @JoinColumn(name = "pharmacy_id"))
    @MapKeyJoinColumn(name = "exam_id", referencedColumnName = "id")
    @Column(name = "price")
    private Map<Exam, Double> examPriceList;

    @Column(name = "rating")
    private double rating;

    public Pharmacy() {

    }

    public Pharmacy(Long id, String name, String address, String about, List<Dermatologist> dermatologists, List<Pharmacist> pharmacists, Map<Medicine, MedicineStatus> medicine, Map<Exam, Double> examPriceList, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.about = about;
        this.dermatologists = dermatologists;
        this.pharmacists = pharmacists;
        this.medicine = medicine;
        this.examPriceList = examPriceList;
        this.rating = rating;
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

    public Map<Medicine, MedicineStatus> getMedicine() {
        return medicine;
    }

    public void setMedicine(Map<Medicine, MedicineStatus> medicine) {
        this.medicine = medicine;
    }

    public Map<Exam, Double> getExamPriceList() {
        return examPriceList;
    }

    public void setExamPriceList(Map<Exam, Double> examPriceList) {
        this.examPriceList = examPriceList;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getCurrentMedicinePrice(Medicine medicine){
        List<MedicinePriceListItem> prices = this.medicine.get(medicine).getPrices();
        return currentPrice(prices);
    }

    private Double currentPrice(List<MedicinePriceListItem> prices) {
        Calendar calendar = Calendar.getInstance();

        Optional<MedicinePriceListItem> currentPriceListItem = prices.stream().filter(price ->
                (calendar.compareTo(price.getTimeInterval().getStart()) >= 0 && calendar.compareTo(price.getTimeInterval().getEnd()) <= 0)).findAny();

        if (currentPriceListItem.isEmpty()) {
            return 0.0;
        } else {
            return currentPriceListItem.get().getPrice();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return Objects.equals(id, pharmacy.id) &&
                Objects.equals(name, pharmacy.name) &&
                Objects.equals(address, pharmacy.address) &&
                Objects.equals(about, pharmacy.about) &&
                Objects.equals(dermatologists, pharmacy.dermatologists) &&
                Objects.equals(pharmacists, pharmacy.pharmacists) &&
                Objects.equals(medicine, pharmacy.medicine) &&
                Objects.equals(examPriceList, pharmacy.examPriceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, about, dermatologists, pharmacists, medicine, examPriceList);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", about='" + about + '\'' +
                ", dermatologists=" + dermatologists +
                ", pharmacists=" + pharmacists +
                ", medicine=" + medicine +
                ", examPriceList=" + examPriceList +
                ", rating=" + rating +
                '}';
    }
}
