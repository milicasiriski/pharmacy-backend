package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;

import java.util.List;


@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy findPharmacyByPharmacyAdmin(Long pharmacyAdminId) {
        return pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdminId);
    }

    @Override
    public Pharmacy save(PharmacyDto dto) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setAbout(dto.getAbout());
        pharmacy.setAddress(dto.getAddress());
        pharmacy.setName(dto.getName());
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacy;
    }
}