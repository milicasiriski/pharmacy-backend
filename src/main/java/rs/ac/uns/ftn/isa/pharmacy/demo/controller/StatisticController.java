package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.ExamStatisticDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.IncomeStatisticDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.MedicineStatisticDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.StatisticService;

@Controller
@RequestMapping(value = "/statistic", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/exam/{year}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<ExamStatisticDto> getExamStatistic(@PathVariable Integer year) {
        return new ResponseEntity<>(statisticService.calculateStatisticForExams(year), HttpStatus.OK);
    }

    @GetMapping("/medicine/{year}/{medicineId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<MedicineStatisticDto> getMedicineStatistic(@PathVariable Integer year, @PathVariable Long medicineId) {
        return new ResponseEntity<>(statisticService.calculateStatisticForMedicine(year, medicineId), HttpStatus.OK);
    }

    @GetMapping("/income/{year}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<IncomeStatisticDto> getIncomeStatistic(@PathVariable Integer year) {
        return new ResponseEntity<>(statisticService.calculateIncomeStatistic(year), HttpStatus.OK);
    }

}
