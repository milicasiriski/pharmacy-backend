package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
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

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Medicine save(MedicineDto dto) throws NoMedicineFoundException{
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
}
