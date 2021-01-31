package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.List;

public class PharmacyProfileDto implements Serializable {

    PharmacyDto pharmacy;
    List<DermatologistDto> dermatologists;
    List<PharmacistDto> pharmacists;
    List<MedicinesBasicInfoDto> medicines;

    public PharmacyProfileDto() {

    }

    public PharmacyProfileDto(PharmacyDto pharmacy, List<DermatologistDto> dermatologists, List<PharmacistDto> pharmacists, List<MedicinesBasicInfoDto> medicines) {
        this.medicines = medicines;
        this.pharmacy = pharmacy;
        this.dermatologists = dermatologists;
        this.pharmacists = pharmacists;
    }

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public List<DermatologistDto> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(List<DermatologistDto> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public List<PharmacistDto> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(List<PharmacistDto> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public List<MedicinesBasicInfoDto> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicinesBasicInfoDto> medicines) {
        this.medicines = medicines;
    }
}
