package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.ExamStatisticDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.IncomeStatisticDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.MedicineStatisticDto;

public interface StatisticService {

    ExamStatisticDto calculateStatisticForExams(Integer year);

    MedicineStatisticDto calculateStatisticForMedicine(Integer year, Long medicineId);

    IncomeStatisticDto calculateIncomeStatistic(Integer year);
}
