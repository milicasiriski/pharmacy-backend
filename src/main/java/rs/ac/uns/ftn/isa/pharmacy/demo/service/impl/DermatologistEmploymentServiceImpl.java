package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistShiftDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistEmploymentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DermatologistEmploymentServiceImpl implements DermatologistEmploymentService {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public DermatologistEmploymentServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public DermatologistShiftDto getDermatologistShifts(Long dermatologistId) {
        Dermatologist dermatologist = getDermatologistById(dermatologistId);
        DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), dermatologist.getEmail(), dermatologistId);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Dermatologist, Employment> dermatologists = getDermatologistsByPharmacy(getPharmacyAdmin());

        return new DermatologistShiftDto(dermatologistDto, populateWeekSchedule(dateFormat, dermatologists.get(dermatologist).getShifts()));
    }

    public List<String> populateWeekSchedule(DateFormat dateFormat, Map<DaysOfWeek, TimeInterval> shifts) {
        List<String> dermatologistShifts = new ArrayList<>();

        EnumSet.allOf(DaysOfWeek.class)
                .forEach(day -> {
                    if(shifts.containsKey(day)) {
                        dermatologistShifts.add(dateFormat.format(shifts.get(day).getStart().getTime()).split(" ")[1]
                                + '-' + dateFormat.format(shifts.get(day).getEnd().getTime()).split(" ")[1]);
                    } else {
                        dermatologistShifts.add("/ - /");
                    }
                });

        return dermatologistShifts;
    }

    public Dermatologist getDermatologistById(Long dermatologistId) {
        Map<Dermatologist, Employment> dermatologists = getDermatologistsByPharmacy(getPharmacyAdmin());

        return (Dermatologist) dermatologists.keySet()
                .stream()
                .filter(d -> d.getId().equals(dermatologistId)).toArray()[0];
    }

    private PharmacyAdmin getPharmacyAdmin() {
        return (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Map<Dermatologist, Employment> getDermatologistsByPharmacy(PharmacyAdmin pharmacyAdmin) {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());
        return pharmacy.getDermatologists();
    }
}
