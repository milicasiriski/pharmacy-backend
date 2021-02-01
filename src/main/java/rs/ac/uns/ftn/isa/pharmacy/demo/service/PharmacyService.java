package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;

import java.util.List;

public interface PharmacyService {
    List<PharmacyDto> findAll();

    Pharmacy findPharmacyByPharmacyAdmin(Long pharmacyAdminId);

    Pharmacy save(PharmacyDto dto);
}
