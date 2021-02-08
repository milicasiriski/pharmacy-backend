package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.statisticdto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.ExamRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicinePurchaseRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.StatisticService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final ExamRepository examRepository;
    private final MedicinePurchaseRepository medicinePurchaseRepository;

    @Autowired
    public StatisticServiceImpl(ExamRepository examRepository, MedicinePurchaseRepository medicinePurchaseRepository) {
        this.examRepository = examRepository;
        this.medicinePurchaseRepository = medicinePurchaseRepository;
    }

    @Override
    public ExamStatisticDto calculateStatisticForExams() {
        int year = 2021;
        Long pharmacyId = 4L;

        List<ExamYearStatisticDto> examsDto = new ArrayList<>();
        List<Integer> perMonth = new ArrayList<>();
        List<Integer> perQuarter = new ArrayList<>();

        for (int i = -2; i < 3; i++) {
            Calendar currentYear = generateYear(year + i);
            Calendar nextYear = generateYear(year + i + 1);
            List<Exam> exams = examRepository.getExamByDateAndPharmacy(currentYear, nextYear, pharmacyId);
            ExamYearStatisticDto examYearStatisticDto = new ExamYearStatisticDto(year + i, exams.size());
            examsDto.add(examYearStatisticDto);
        }

        for (int i = 0; i < 11; i++) {
            Calendar currentMonth = generateMonth(i, year);
            Calendar nextMonth = generateMonth(i + 1, year);
            List<Exam> exams = examRepository.getExamByDateAndPharmacy(currentMonth, nextMonth, pharmacyId);
            int count = exams.size();
            perMonth.add(count);
        }

        for (int i = 0; i < 11; i = i + 3) {
            int end = i + 3;
            Calendar monthStart = generateMonth(i, year);
            Calendar monthEnd = generateMonth(end, year);
            List<Exam> exams = examRepository.getExamByDateAndPharmacy(monthStart, monthEnd, pharmacyId);
            int count = exams.size();
            perQuarter.add(count);
        }

        ExamMonthStatisticDto examMonthStatisticDto = new ExamMonthStatisticDto(perMonth);
        ExamQuarterStatisticDto examQuarterStatisticDto = new ExamQuarterStatisticDto(perQuarter);

        return new ExamStatisticDto(examMonthStatisticDto, examsDto, examQuarterStatisticDto);
    }

    @Override
    public MedicineStatisticDto calculateStatisticForMedicine() {

        int year = 2021;
        Long pharmacyId = 4L;
        Long medicineId = 1L;

        List<MedicineYearStatisticDto> medicinesDto = new ArrayList<>();
        List<Integer> perMonth = new ArrayList<>();
        List<Integer> perQuarter = new ArrayList<>();

        for (int i = -2; i < 3; i++) {
            Calendar currentYear = generateYear(year + i);
            Calendar nextYear = generateYear(year + i + 1);
            Integer amount = medicinePurchaseRepository.getMedicinePurchaseByPharmacyAndDate(currentYear, nextYear, pharmacyId, medicineId);
            if (amount == null) {
                amount = 0;
            }
            MedicineYearStatisticDto medicineYearStatisticDto = new MedicineYearStatisticDto(year + i, amount);
            medicinesDto.add(medicineYearStatisticDto);
        }

        for (int i = 0; i < 11; i++) {
            Calendar currentMonth = generateMonth(i, year);
            Calendar nextMonth = generateMonth(i + 1, year);
            Integer amount = medicinePurchaseRepository.getMedicinePurchaseByPharmacyAndDate(currentMonth, nextMonth, pharmacyId, medicineId);
            if (amount == null) {
                amount = 0;
            }
            perMonth.add(amount);
        }

        for (int i = 0; i < 11; i = i + 3) {
            int end = i + 3;
            Calendar monthStart = generateMonth(i, year);
            Calendar monthEnd = generateMonth(end, year);
            Integer amount = medicinePurchaseRepository.getMedicinePurchaseByPharmacyAndDate(monthStart, monthEnd, pharmacyId, medicineId);
            if (amount == null) {
                amount = 0;
            }
            perQuarter.add(amount);
        }

        MedicineMonthStatisticDto medicineMonthStatisticDto = new MedicineMonthStatisticDto(perMonth);
        MedicineQuarterStatisticDto medicineQuarterStatisticDto = new MedicineQuarterStatisticDto(perQuarter);

        return new MedicineStatisticDto(medicineMonthStatisticDto, medicinesDto, medicineQuarterStatisticDto);
    }

    private Calendar generateYear(Integer year) {
        Calendar newYear = Calendar.getInstance();
        newYear.set(Calendar.YEAR, year);
        newYear.set(Calendar.MONTH, 0);
        newYear.set(Calendar.DAY_OF_MONTH, 1);

        return newYear;
    }

    private Calendar generateMonth(Integer month, Integer year) {
        Calendar newMonth = Calendar.getInstance();
        newMonth.set(Calendar.YEAR, year);
        newMonth.set(Calendar.MONTH, month);
        newMonth.set(Calendar.DAY_OF_MONTH, 1);

        return newMonth;
    }
}
