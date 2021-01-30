package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyAdminExamDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.DermatologistEmploymentService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.ExamService;

import java.util.Calendar;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {

    private final DermatologistEmploymentService dermatologistEmploymentService;
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public ExamServiceImpl(DermatologistEmploymentService dermatologistEmploymentService, PharmacyRepository pharmacyRepository) {
        this.dermatologistEmploymentService = dermatologistEmploymentService;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public void createExam(PharmacyAdminExamDto pharmacyAdminExamDto) {
        Exam exam = new Exam(pharmacyAdminExamDto.getPrice(), generateExamTimeInterval(pharmacyAdminExamDto));
        Dermatologist dermatologist = dermatologistEmploymentService.getDermatologistById(Long.parseLong(pharmacyAdminExamDto.getDermatologistId()));

        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getId());
        Map<Dermatologist, Employment> dermatologists = pharmacy.getDermatologists();
        Employment employment = dermatologists.get(dermatologist);

        employment.getExams().add(exam);
        pharmacyRepository.save(pharmacy);
    }

    private TimeInterval generateExamTimeInterval(PharmacyAdminExamDto pharmacyAdminExamDto) {
        Calendar examStart = Calendar.getInstance();
        examStart.setTime(pharmacyAdminExamDto.getExamStart());
        Calendar examEnd = Calendar.getInstance();

        examEnd.set(Calendar.YEAR, examStart.get(Calendar.YEAR));
        examEnd.set(Calendar.MONTH, examStart.get(Calendar.MONTH));
        examEnd.set(Calendar.DAY_OF_MONTH, examStart.get(Calendar.DAY_OF_MONTH));
        examEnd.set(Calendar.HOUR, examStart.get(Calendar.HOUR));
        examEnd.set(Calendar.MINUTE, examStart.get(Calendar.MINUTE));
        examEnd.set(Calendar.SECOND, examStart.get(Calendar.SECOND));
        examEnd.add(Calendar.MINUTE, pharmacyAdminExamDto.getDuration());

        return new TimeInterval(examStart, examEnd);
    }
}