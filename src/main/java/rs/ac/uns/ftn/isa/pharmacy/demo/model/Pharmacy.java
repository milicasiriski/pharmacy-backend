package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Pharmacy {

    private String name;
    private String address;
    private String about;
    private List<Dermatologist> dermatologists;
    private List<Pharmacist> pharmacists;
    private List<Medicine> drugs;
    private HashMap<Medicine, Double> drugsPriceList;
    private HashMap<Exam, Double> examinePriceList;

    public Pharmacy() {

    }

    public Pharmacy(String name, String address, String about, List<Dermatologist> dermatologists, List<Pharmacist> pharmacists, List<Medicine> drugs, HashMap<Medicine, Double> drugsPriceList, HashMap<Exam, Double> examinePriceList) {
        this.name = name;
        this.address = address;
        this.about = about;
        this.dermatologists = dermatologists;
        this.pharmacists = pharmacists;
        this.drugs = drugs;
        this.drugsPriceList = drugsPriceList;
        this.examinePriceList = examinePriceList;
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

    public List<Medicine> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Medicine> drugs) {
        this.drugs = drugs;
    }

    public HashMap<Medicine, Double> getDrugsPriceList() {
        return drugsPriceList;
    }

    public void setDrugsPriceList(HashMap<Medicine, Double> drugsPriceList) {
        this.drugsPriceList = drugsPriceList;
    }

    public HashMap<Exam, Double> getExaminePriceList() {
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
}
