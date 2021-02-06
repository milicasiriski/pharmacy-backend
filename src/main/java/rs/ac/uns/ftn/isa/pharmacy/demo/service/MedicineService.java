package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineNameUuidDto;

import java.util.List;

public interface MedicineService {

    Medicine save(MedicineDto dto) throws NoMedicineFoundException;

    List<MedicineDto> getAll();

    List<List<MedicineNameUuidDto>> getAlternativesGroups();

    List<MedicineDto> getMedicineIfDoesntExistInPharmacy();
}
