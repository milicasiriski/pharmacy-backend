package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.PharmacyAdmin;
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
    public ExamStatisticDto calculateStatisticForExams(Integer year) {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long pharmacyId = pharmacyAdmin.getPharmacy().getId();

        List<ExamYearStatisticDto> examsDto = calculateExamStatisticPerYear(year, pharmacyId);
        List<Integer> perMonth = calculateExamStatisticPerMonth(year, pharmacyId);
        List<Integer> perQuarter = calculateExamStatisticPerQuarter(year, pharmacyId);

        ExamMonthStatisticDto examMonthStatisticDto = new ExamMonthStatisticDto(perMonth);
        ExamQuarterStatisticDto examQuarterStatisticDto = new ExamQuarterStatisticDto(perQuarter);

        return new ExamStatisticDto(examMonthStatisticDto, examsDto, examQuarterStatisticDto);
    }

    @Override
    public MedicineStatisticDto calculateStatisticForMedicine(Integer year, Long medicineId) {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long pharmacyId = pharmacyAdmin.getPharmacy().getId();

        List<MedicineYearStatisticDto> medicinesDto = calculateMedicineStatisticPerYear(year, medicineId, pharmacyId);
        List<Integer> perMonth = calculateMedicineStatisticPerMonth(year, medicineId, pharmacyId);
        List<Integer> perQuarter = calculateMedicineStatisticPerQuarter(year, medicineId, pharmacyId);

        MedicineMonthStatisticDto medicineMonthStatisticDto = new MedicineMonthStatisticDto(perMonth);
        MedicineQuarterStatisticDto medicineQuarterStatisticDto = new MedicineQuarterStatisticDto(perQuarter);

        return new MedicineStatisticDto(medicineMonthStatisticDto, medicinesDto, medicineQuarterStatisticDto);
    }

    @Override
    public IncomeStatisticDto calculateIncomeStatistic(Integer year) {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long pharmacyId = pharmacyAdmin.getPharmacy().getId();
        List<Double> perMonth = calculateIncomeStatisticPerMonth(year, pharmacyId);

        return new IncomeStatisticDto(perMonth);
    }

    private List<Double> calculateIncomeStatisticPerMonth(Integer year, Long pharmacyId) {
        double income;
        List<Double> perMonth = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Calendar currentMonth = generateMonth(i, year);
            Calendar nextMonth = generateMonth(i + 1, year);
            Double medicineIncome = medicinePurchaseRepository.getMedicineIncomeByPharmacyAndDate(currentMonth, nextMonth, pharmacyId);
            double examIncome = 0.0;

            List<Exam> exams = examRepository.getExamByDateAndPharmacy(currentMonth, nextMonth, pharmacyId);

            for (Exam exam : exams) {
                examIncome += exam.getPrice();
            }

            if (medicineIncome == null) {
                medicineIncome = 0.0;
            }

            income = examIncome + medicineIncome;
            perMonth.add(income);
        }
        return perMonth;
    }

    private List<ExamYearStatisticDto> calculateExamStatisticPerYear(Integer year, Long pharmacyId) {
        List<ExamYearStatisticDto> examsDto = new ArrayList<>();

        for (int i = -2; i < 3; i++) {
            Calendar currentYear = generateYear(year + i);
            Calendar nextYear = generateYear(year + i + 1);
            List<Exam> exams = examRepository.getExamByDateAndPharmacy(currentYear, nextYear, pharmacyId);
            ExamYearStatisticDto examYearStatisticDto = new ExamYearStatisticDto(year + i, exams.size());
            examsDto.add(examYearStatisticDto);
        }
        return examsDto;
    }

    private List<Integer> calculateExamStatisticPerQuarter(Integer year, Long pharmacyId) {
        List<Integer> perQuarter = new ArrayList<>();
        for (int i = 0; i < 11; i = i + 3) {
            int end = i + 3;
            Calendar monthStart = generateMonth(i, year);
            Calendar monthEnd = generateMonth(end, year);
            List<Exam> exams = examRepository.getExamByDateAndPharmacy(monthStart, monthEnd, pharmacyId);
            int count = exams.size();
            perQuarter.add(count);
        }
        return perQuarter;
    }

    private List<Integer> calculateExamStatisticPerMonth(Integer year, Long pharmacyId) {
        List<Integer> perMonth = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Calendar currentMonth = generateMonth(i, year);
            Calendar nextMonth = generateMonth(i + 1, year);
            List<Exam> exams = examRepository.getExamByDateAndPharmacy(currentMonth, nextMonth, pharmacyId);
            int count = exams.size();
            perMonth.add(count);
        }
        return perMonth;
    }

    private List<Integer> calculateMedicineStatisticPerQuarter(Integer year, Long medicineId, Long pharmacyId) {
        List<Integer> perQuarter = new ArrayList<>();

        for (int i = 0; i < 11; i = i + 3) {
            int end = i + 3;
            Calendar monthStart = generateMonth(i, year);
            Calendar monthEnd = generateMonth(end, year);
            Integer amount = medicinePurchaseRepository.getMedicineAmountByPharmacyDateAndMedicine(monthStart, monthEnd, pharmacyId, medicineId);
            if (amount == null) {
                amount = 0;
            }
            perQuarter.add(amount);
        }
        return perQuarter;
    }

    private List<Integer> calculateMedicineStatisticPerMonth(Integer year, Long medicineId, Long pharmacyId) {
        List<Integer> perMonth = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Calendar currentMonth = generateMonth(i, year);
            Calendar nextMonth = generateMonth(i + 1, year);
            Integer amount = medicinePurchaseRepository.getMedicineAmountByPharmacyDateAndMedicine(currentMonth, nextMonth, pharmacyId, medicineId);
            if (amount == null) {
                amount = 0;
            }
            perMonth.add(amount);
        }
        return perMonth;
    }

    private List<MedicineYearStatisticDto> calculateMedicineStatisticPerYear(Integer year, Long medicineId, Long pharmacyId) {
        List<MedicineYearStatisticDto> medicinesDto = new ArrayList<>();

        for (int i = -2; i < 3; i++) {
            Calendar currentYear = generateYear(year + i);
            Calendar nextYear = generateYear(year + i + 1);
            Integer amount = medicinePurchaseRepository.getMedicineAmountByPharmacyDateAndMedicine(currentYear, nextYear, pharmacyId, medicineId);
            if (amount == null) {
                amount = 0;
            }
            MedicineYearStatisticDto medicineYearStatisticDto = new MedicineYearStatisticDto(year + i, amount);
            medicinesDto.add(medicineYearStatisticDto);
        }
        return medicinesDto;
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
