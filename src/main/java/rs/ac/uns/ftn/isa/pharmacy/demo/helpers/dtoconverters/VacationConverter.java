package rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.TimeInterval;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestDermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.VacationTimeRequestPharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public interface VacationConverter {

    default List<VacationDto> createResponseForPharmacist(Iterable<VacationTimeRequestPharmacist> vacationTimeRequestPharmacists) {
        List<VacationDto> vacationDtos = new ArrayList<>();
        vacationTimeRequestPharmacists.forEach(vacation -> {
            String vacationInterval = formatVacationTime(vacation.getRequestedTimeForVacation());
            VacationDto vacationDto = new VacationDto(vacation.getPharmacist().getName(), vacation.getPharmacist().getSurname(),
                    vacationInterval, "Pharmacist");
            vacationDtos.add(vacationDto);
        });
        return vacationDtos;
    }

    default List<VacationDto> createResponseForDermatologist(Iterable<VacationTimeRequestDermatologist> vacationTimeRequestDermatologists) {
        List<VacationDto> vacationDtos = new ArrayList<>();
        vacationTimeRequestDermatologists.forEach(vacation -> {
            String vacationInterval = formatVacationTime(vacation.getRequestedTimeForVacation());
            VacationDto vacationDto = new VacationDto(vacation.getDermatologist().getName(), vacation.getDermatologist().getSurname(),
                    vacationInterval, "Dermatologist");
            vacationDtos.add(vacationDto);
        });
        return vacationDtos;
    }

    private String formatVacationTime(TimeInterval requestedTimeForVacation) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startVacation = dateFormat.format(requestedTimeForVacation.getStart().getTime());
        String endVacation = dateFormat.format(requestedTimeForVacation.getEnd().getTime());
        return startVacation + "-" + endVacation;
    }
}
