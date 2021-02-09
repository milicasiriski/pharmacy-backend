package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    @Id
    @SequenceGenerator(name = "pharmacy_sequence_generator", sequenceName = "pharmacy_sequence", initialValue = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_sequence_generator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    @Column(name = "about")
    private String about;

    @Column(name = "pharmacist_exam_price")
    private Double pharmacistExamPrice;

    @Column(name = "pharmacist_exam_duration")
    private Integer pharmacistExamDuration;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dermatologist_employment_mapping",
            joinColumns = {@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dermatologist_employment_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "dermatologist_id")
    private Map<Dermatologist, Employment> dermatologists;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pharmacy_pharmacists",
            joinColumns = {@JoinColumn(name = "pharmacy_id")},
            inverseJoinColumns = {@JoinColumn(name = "pharmacist_id")})
    private List<Pharmacist> pharmacists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pharmacy_subscribers",
            joinColumns = {@JoinColumn(name = "pharmacy_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")})
    private List<Patient> subscribers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacy_id")
    private List<PharmacyAdmin> pharmacyAdmins;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacy_id")
    private List<Promotion> pharmacyPromotions;

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
    private Double rating;

    public Pharmacy() {

    }

    public Pharmacy(String name, Address address, String about) {
        this.name = name;
        this.address = address;
        this.about = about;
        this.dermatologists = new HashMap<>();
        this.pharmacyAdmins = new ArrayList<>();
        this.examPriceList = new HashMap<>();
    }

    public Pharmacy(String name, Address address, String about, Double pharmacistExamPrice, Integer pharmacistExamDuration, Map<Dermatologist, Employment> dermatologists, List<Pharmacist> pharmacists, List<PharmacyAdmin> pharmacyAdmins, List<Promotion> pharmacyPromotions, Map<Medicine, MedicineStatus> medicine, Map<Exam, Double> examPriceList, Double rating, List<Patient> subscribers) {
        this.name = name;
        this.address = address;
        this.about = about;
        this.pharmacistExamPrice = pharmacistExamPrice;
        this.pharmacistExamDuration = pharmacistExamDuration;
        this.dermatologists = dermatologists;
        this.pharmacists = pharmacists;
        this.subscribers = subscribers;
        this.pharmacyAdmins = pharmacyAdmins;
        this.pharmacyPromotions = pharmacyPromotions;
        this.medicine = medicine;
        this.examPriceList = examPriceList;
        this.rating = rating;
    }

    public Pharmacy(Long id, String name, Address address, String about, Map<Dermatologist, Employment> dermatologists, List<Pharmacist> pharmacists, Map<Medicine, MedicineStatus> medicine, Map<Exam, Double> examPriceList, Double rating) {
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

    public void addMedicinesOnStock(Map<Medicine, Integer> medicineAmount) throws EntityNotFoundException {
        medicineAmount.keySet().forEach(m -> {
            if (this.getMedicine().containsKey(m)) {
                this.getMedicine().get(m).addMedicineAmount(medicineAmount.get(m));
            } else {
                throw new EntityNotFoundException();
            }
        });
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Map<Dermatologist, Employment> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(Map<Dermatologist, Employment> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public List<PharmacyAdmin> getPharmacyAdmins() {
        return pharmacyAdmins;
    }

    public void setPharmacyAdmins(List<PharmacyAdmin> pharmacyAdmins) {
        this.pharmacyAdmins = pharmacyAdmins;
    }

    public List<Promotion> getPharmacyPromotions() {
        return pharmacyPromotions;
    }

    public void setPharmacyPromotions(List<Promotion> pharmacyPromotions) {
        this.pharmacyPromotions = pharmacyPromotions;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Double getPharmacistExamPrice() {
        return pharmacistExamPrice;
    }

    public void setPharmacistExamPrice(Double pharmacistExamPrice) {
        this.pharmacistExamPrice = pharmacistExamPrice;
    }

    public Integer getPharmacistExamDuration() {
        return pharmacistExamDuration;
    }

    public void setPharmacistExamDuration(Integer pharmacistExamDuration) {
        this.pharmacistExamDuration = pharmacistExamDuration;
    }

    public List<Patient> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Patient> subscribers) {
        this.subscribers = subscribers;
    }

    public void addDermatologist(Dermatologist dermatologist, Employment employment) {
        if (!dermatologists.containsKey(dermatologist)) {
            dermatologists.put(dermatologist, employment);
        }
    }

    public double getCurrentMedicinePrice(Medicine medicine) {
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
                Objects.equals(subscribers, pharmacy.subscribers) &&
                Objects.equals(pharmacyAdmins, pharmacy.pharmacyAdmins) &&
                Objects.equals(pharmacyPromotions, pharmacy.pharmacyPromotions) &&
                Objects.equals(medicine, pharmacy.medicine) &&
                Objects.equals(examPriceList, pharmacy.examPriceList) &&
                Objects.equals(rating, pharmacy.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", about='" + about + '\'' +
                ", dermatologists=" + dermatologists +
                ", pharmacists=" + pharmacists +
                ", subscribers=" + subscribers +
                ", pharmacyAdmins=" + pharmacyAdmins +
                ", pharmacyPromotions=" + pharmacyPromotions +
                ", medicine=" + medicine +
                ", examPriceList=" + examPriceList +
                ", rating=" + rating +
                '}';
    }
}
