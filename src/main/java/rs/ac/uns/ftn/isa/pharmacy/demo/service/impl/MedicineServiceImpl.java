package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.MedicineService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

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
        return medicineRepository.save(medicine);
    }

    private List<Medicine> createMedicineAlternatives(MedicineDto dto) {
        List<Medicine> alternatives = new ArrayList<>();
        for (String m : dto.getAlternatives()) {
            Medicine resultMedicine = medicineRepository.findByUuid(m);
            if (resultMedicine != null) {
                alternatives.add(resultMedicine);
            }
            else{
                throw new RuntimeException(
                        "No medicine was found"
                );
            }
        }
        return alternatives;
    }

    private Medicine createBasicMedicine(MedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setComposition(dto.getComposition());
        medicine.setDescription(dto.getDescription());
        medicine.setForm(dto.getForm());
        medicine.setManufacturer(dto.getManufacturer());
        medicine.setName(dto.getName());
        medicine.setPrescribed(dto.getPrescribed());
        medicine.setRecommendedDose(dto.getRecommendedDose());
        medicine.setSideEffects(dto.getSideEffects());
        medicine.setType(dto.getType());
        medicine.setUuid(dto.getUuid());
        return medicine;
    }
}
