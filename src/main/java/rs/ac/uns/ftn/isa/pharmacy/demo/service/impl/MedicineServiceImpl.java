package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters.MedicineConverter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.MedicineStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.PharmacyAdmin;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineNameUuidDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService, MedicineConverter {

    private final MedicineRepository medicineRepository;
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, PharmacyRepository pharmacyRepository) {
        this.medicineRepository = medicineRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Medicine save(MedicineDto dto) throws NoMedicineFoundException {
        Medicine medicine = createBasicMedicine(dto);
        List<Medicine> alternatives = createMedicineAlternatives(dto);
        medicine.setAlternatives(alternatives);
        medicine = medicineRepository.save(medicine);
        updateAlternatives(medicine);
        return medicineRepository.save(medicine);
    }

    private void updateAlternatives(Medicine medicine) {
        medicine.getAlternatives().forEach(
                alternativeMedicine -> {
                    Optional<Medicine> altOptional = medicineRepository.findById(alternativeMedicine.getId());
                    if (altOptional.isPresent()) {
                        Medicine alternative = altOptional.get();
                        alternative.getAlternatives().add(medicine);
                    }
                }
        );
    }

    //TODO: Vladimir, potential transaction NOSONAR
    private List<Medicine> createMedicineAlternatives(MedicineDto dto) {
        List<Medicine> alternatives = new ArrayList<>();
        if (dto.getAlternatives() != null) {
            for (MedicineNameUuidDto m : dto.getAlternatives()) {
                Medicine resultMedicine = medicineRepository.findByUuid(m.getUuid());
                if (resultMedicine != null) {
                    alternatives.add(resultMedicine);
                } else {
                    throw new NoMedicineFoundException();
                }
            }
        }
        return alternatives;
    }

    @Override
    public List<MedicineDto> getAll() {
        return createResponse(medicineRepository.findAll());
    }

    @Override
    public List<List<MedicineNameUuidDto>> getAlternativesGroups() {
        Iterable<Medicine> allMedicine = medicineRepository.findAll();
        return createAlternativeGroups(allMedicine);
    }

    @Override
    public List<MedicineDto> getMedicineIfDoesntExistInPharmacy() throws EntityNotFoundException {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());

        Map<Medicine, MedicineStatus> medicine = pharmacy.getMedicine();
        List<Medicine> allMedicines = (List<Medicine>) medicineRepository.findAll();
        List<Medicine> medicineThatDoesntExist = new ArrayList<>();

        allMedicines.forEach(m -> {
            if (!medicine.containsKey(m)) {
                medicineThatDoesntExist.add(m);
            }
        });

        return createResponse(medicineThatDoesntExist);
    }
}
