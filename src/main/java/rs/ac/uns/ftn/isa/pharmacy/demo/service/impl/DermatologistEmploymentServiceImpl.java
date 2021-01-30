package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Dermatologist, Employment> dermatologists = getDermatologistsByPharmacy(getPharmacyAdmin());

        DermatologistShiftDto dermatologistShiftDto = new DermatologistShiftDto();
        dermatologistShiftDto.setDermatologist(dermatologist);
        dermatologistShiftDto.setPrice(dermatologists.get(dermatologist).getPrice().toString());
        dermatologistShiftDto.setDurationInMinutes(dermatologists.get(dermatologist).getPrice().toString());

        Map<DaysOfWeek, TimeInterval> shifts = dermatologists.get(dermatologist).getShifts();
        List<String> dermatologistShifts = populateWeekSchedule(dateFormat, shifts);
        dermatologistShiftDto.setHourIntervals(dermatologistShifts);

        return dermatologistShiftDto;
    }

    public List<String> populateWeekSchedule(DateFormat dateFormat, Map<DaysOfWeek, TimeInterval> shifts) {
        List<String> dermatologistShifts = new ArrayList<>();

        EnumSet.allOf(DaysOfWeek.class)
                .forEach(day -> {
                    dermatologistShifts.add(dateFormat.format(shifts.get(day).getStart().getTime()).split(" ")[1]
                            + '-' + dateFormat.format(shifts.get(day).getEnd().getTime()).split(" ")[1]);
                });

        return dermatologistShifts;
    }

    public Dermatologist getDermatologistById(Long dermatologistId) {
        Map<Dermatologist, Employment> dermatologists = getDermatologistsByPharmacy(getPharmacyAdmin());

        return (Dermatologist) dermatologists.keySet()
                .stream()
                .filter(d -> d.getId().equals(dermatologistId)).toArray()[0];
    }

    public PharmacyAdmin getPharmacyAdmin() {
        return (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Map<Dermatologist, Employment> getDermatologistsByPharmacy(PharmacyAdmin pharmacyAdmin) {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());
        return pharmacy.getDermatologists();
    }
}
