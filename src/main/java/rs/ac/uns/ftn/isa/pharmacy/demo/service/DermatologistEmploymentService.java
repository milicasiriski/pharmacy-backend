package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistShiftDto;

public interface DermatologistEmploymentService {

    DermatologistShiftDto getDermatologistShifts(Long dermatologistId);

    Dermatologist getDermatologistById(Long dermatologistId);
}
