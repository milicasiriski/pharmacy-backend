package rs.ac.uns.ftn.isa.pharmacy.demo.service;


import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;

import java.util.List;

public interface PatientService {
    Iterable<MedicinesBasicInfoDto> getAllAllergies();

    void updateAllergies(List<MedicinesBasicInfoDto> medicine);

    Iterable<MedicinesBasicInfoDto> getAllMedicine();

    boolean hasCurrentUserExceededPenaltyPoints();
}
