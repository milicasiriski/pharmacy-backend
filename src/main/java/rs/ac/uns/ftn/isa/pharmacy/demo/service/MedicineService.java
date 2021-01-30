package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineNameUuidDto;

import java.util.List;

public interface MedicineService {

    Medicine save(MedicineDto dto);

    List<MedicineDto> getAll();

    List<List<MedicineNameUuidDto>> getAlternativesGroups();
}
