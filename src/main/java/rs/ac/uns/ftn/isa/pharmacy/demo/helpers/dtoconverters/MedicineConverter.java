package rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineNameUuidDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface MedicineConverter {

    default List<MedicineDto> createResponse(Iterable<Medicine> medicines) {
        List<MedicineDto> medicinesDto = new ArrayList<>();
        medicines.forEach(medicine -> {
                    MedicineDto medicineDto = new MedicineDto(
                            medicine.getId(),
                            medicine.getUuid(),
                            medicine.getName(),
                            medicine.getDescription(),
                            medicine.getManufacturer(),
                            medicine.getComposition(),
                            medicine.getForm(),
                            medicine.getType(),
                            medicine.isPrescribed(),
                            medicine.getRecommendedDose(),
                            medicine.getSideEffects(),
                            null,
                            medicine.getPoints(),
                            medicine.getRatings());
                    List<MedicineNameUuidDto> alternatives = new ArrayList<>();
                    medicine.getAlternatives().forEach(m ->
                            alternatives.add(new MedicineNameUuidDto(m.getUuid(), m.getName()))
                    );
                    medicineDto.setAlternatives(alternatives);
                    medicineDto.setPoints(medicine.getPoints());
                    medicinesDto.add(medicineDto);
                }
        );
        return medicinesDto;
    }


    default List<List<MedicineNameUuidDto>> createAlternativeGroups(Iterable<Medicine> allMedicine) {
        List<List<MedicineNameUuidDto>> alternativesGroups = new ArrayList<>();
        Set<String> uuidsUsed = new HashSet<>();
        allMedicine.forEach(
                medicine -> {
                    String medicineUuid = medicine.getUuid();
                    if (!uuidsUsed.contains(medicineUuid)) {
                        String medicineName = medicine.getName();
                        List<MedicineNameUuidDto> alternatives = new ArrayList<>();
                        alternatives.add(new MedicineNameUuidDto(medicineUuid, medicineName));
                        uuidsUsed.add(medicine.getUuid());
                        medicine.getAlternatives().forEach(
                                alternative -> {
                                    String alternativeUuid = alternative.getUuid();
                                    if (!uuidsUsed.contains(alternativeUuid)) {
                                        String alternativeName = alternative.getName();
                                        alternatives.add(new MedicineNameUuidDto(alternativeUuid, alternativeName));
                                        uuidsUsed.add(alternative.getUuid());
                                    }
                                }
                        );
                        if (!alternatives.isEmpty()) {
                            alternativesGroups.add(alternatives);
                        }
                    }
                }
        );
        return alternativesGroups;
    }

    default Medicine createBasicMedicine(MedicineDto dto) {
        Medicine medicine = new Medicine(dto.getUuid(), dto.getName(), dto.getDescription(), dto.getManufacturer(),
                dto.getComposition(), dto.getForm(), dto.getType(), dto.isPrescribed(), dto.getRecommendedDose(),
                dto.getSideEffects(), dto.getPoints());
        return medicine;
    }
}



