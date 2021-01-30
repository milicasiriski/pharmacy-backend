package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters.MedicineConverter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineNameUuidDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService, MedicineConverter {

    private MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Medicine save(MedicineDto dto) {
        Medicine medicine = createBasicMedicine(dto);
        List<Medicine> alternatives = createMedicineAlternatives(dto);
        medicine.setAlternatives(alternatives);
        medicine = medicineRepository.save(medicine);
        updateAlternatives(medicine);
        return medicineRepository.save(medicine);
    }

    private void updateAlternatives(Medicine medicine) {
        Medicine finalMedicine = medicine;
        medicine.getAlternatives().forEach(
                alternativeMedicine -> {
                    Optional<Medicine> altOptional = medicineRepository.findById(alternativeMedicine.getId());
                    if (!altOptional.isEmpty()) {
                        Medicine alternative = altOptional.get();
                        alternative.getAlternatives().add(finalMedicine);
                    }
                }
        );
    }

    //TODO: Vladimir, potential transaction
    private List<Medicine> createMedicineAlternatives(MedicineDto dto) {
        List<Medicine> alternatives = new ArrayList<>();
        if (dto.getAlternatives() != null) {
            for (MedicineNameUuidDto m : dto.getAlternatives()) {
                Medicine resultMedicine = medicineRepository.findByUuid(m.getUuid());
                if (resultMedicine != null) {
                    alternatives.add(resultMedicine);
                } else {
                    throw new RuntimeException(
                            "No medicine was found!"
                    );
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
        List<List<MedicineNameUuidDto>> alternativesGroups = createAlternativeGroups(allMedicine);
        return alternativesGroups;
    }

    private Medicine createBasicMedicine(MedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setComposition(dto.getComposition());
        medicine.setDescription(dto.getDescription());
        medicine.setForm(dto.getForm());
        medicine.setManufacturer(dto.getManufacturer());
        medicine.setName(dto.getName());
        medicine.setPrescribed(dto.isPrescribed());
        medicine.setRecommendedDose(dto.getRecommendedDose());
        medicine.setSideEffects(dto.getSideEffects());
        medicine.setType(dto.getType());
        medicine.setUuid(dto.getUuid());
        medicine.setPoints(dto.getPoints());
        return medicine;
    }
}
