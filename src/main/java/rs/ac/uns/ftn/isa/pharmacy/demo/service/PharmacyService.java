package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.AddDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyProfileDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.RatingFilter;

import java.util.List;

public interface PharmacyService {
    List<PharmacyDto> findAll();

    List<PharmacyDto> findAll(RatingFilter ratingFilter, double distance, double userLon, double userLat);

    Pharmacy findPharmacyByPharmacyAdmin(Long pharmacyAdminId);

    Pharmacy save(PharmacyDto dto);

    List<PharmacyNameAndAddressDto> findPharmaciesBasicInfo();

    PharmacyProfileDto findPharmacyById(Long pharmacyId);

    PharmacyDto getPharmacyInfoByAdmin();

    void updatePharmacyInfo(PharmacyDto pharmacyDto);

    void addMedicine(Long medicineId);

    void removeMedicine(Long medicineId);

    void removePharmacist(Long pharmacistId);

    void removeDermatologist(Long dermatologistId);

    void addDermatologist(AddDermatologistDto addDermatologistDto);

    PharmacyProfileDto getAllPharmacyInfoByPharmacyAdmin();
}
