package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.VacationRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.VacationService;

import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {

    private final VacationRepository vacationRepository;

    @Autowired
    public VacationServiceImpl(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @Override
    public Iterable<VacationTimeRequestPharmacist> getAllPharmacistsVacation() {

     //   List<VacationTimeRequestPharmacist> vacationList = vacationRepository.getAllPharmacistsVacation();
        return vacationRepository.findAll();
    }

    @Override
    public List<VacationTimeRequestDermatologist> getAllDermatologistsVacation() {
        return null;
    }

//    @Override
//    public List<VacationTimeRequestDermatologist> getAllDermatologistsVacation() {
//        return vacationRepository.getAllDermatologistVacation();
//    }
}
