package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.ExamStatisticDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.MedicineStatisticDto;

public interface StatisticService {

    ExamStatisticDto calculateStatisticForExams();

    MedicineStatisticDto calculateStatisticForMedicine();
}
