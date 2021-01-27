package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineDto;

public interface MedicineService {

    Medicine save(MedicineDto dto);

}
