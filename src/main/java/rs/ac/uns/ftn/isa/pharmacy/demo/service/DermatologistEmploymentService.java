package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistShiftDto;

public interface DermatologistEmploymentService {

    Iterable<DermatologistShiftDto> getDermatologistShifts();
}
