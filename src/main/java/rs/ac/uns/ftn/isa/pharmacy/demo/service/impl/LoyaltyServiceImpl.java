package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.LoyaltyProgram;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LoyaltyProgramDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.ExamStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.ExamRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.LoyaltyProgramRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.LoyaltyService;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LoyaltyServiceImpl implements LoyaltyService {

    private final LoyaltyProgramRepository loyaltyProgramRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public LoyaltyServiceImpl(LoyaltyProgramRepository loyaltyProgramRepository, ExamRepository examRepository, UserRepository userRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void update(LoyaltyProgramDto dto) {
        Optional<LoyaltyProgram> loyaltyOptional = loyaltyProgramRepository.findById(LoyaltyProgram.defaultId);
        LoyaltyProgram loyaltyProgram;
        if (!validInformation(dto)) {
            throw new BadRequestException();
        }
        if (loyaltyOptional.isEmpty()) {
            loyaltyProgram = new LoyaltyProgram(LoyaltyProgram.defaultId, dto.getSilverMinimumPoints(), dto.getSilverDiscount(), dto.getGoldMinimumPoints(), dto.getGoldDiscount(), dto.getExamPoints());
        } else {
            loyaltyProgram = loyaltyOptional.get();
            loyaltyProgram.setGoldDiscount(dto.getGoldDiscount());
            loyaltyProgram.setGoldMinimumPoints(dto.getGoldMinimumPoints());
            loyaltyProgram.setSilverDiscount(dto.getSilverDiscount());
            loyaltyProgram.setSilverMinimumPoints(dto.getSilverMinimumPoints());

        }
        loyaltyProgramRepository.save(loyaltyProgram);
    }

    private boolean validInformation(LoyaltyProgramDto dto) {
        double goldDiscount = dto.getGoldDiscount();
        double silverDiscount = dto.getSilverDiscount();
        int examPoints = dto.getExamPoints();
        int silverMinimumPoints = dto.getSilverMinimumPoints();
        int goldMinimumPoints = dto.getGoldMinimumPoints();
        return goldDiscount > 0 && goldDiscount <= 1 && silverDiscount > 0 && silverDiscount <= 1 && silverDiscount >= goldDiscount &&
                examPoints >= 0 && silverMinimumPoints > 0 && goldMinimumPoints > 1 && silverMinimumPoints <= goldMinimumPoints;
    }

    @Override
    public double getDiscount() {
        try {
            Optional<LoyaltyProgram> loyaltyOptional = loyaltyProgramRepository.findById(LoyaltyProgram.defaultId);
            if (loyaltyOptional.isEmpty()) {
                return 1;
            }
            LoyaltyProgram loyaltyProgram = loyaltyOptional.get();
            Patient patient;
            try {
                patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            } catch (Exception e) {
                return 1;
            }
            Double discount = loyaltyProgram.getDiscount(patient.getLoyaltyPoints());
            return discount;
        } catch (NoSuchElementException noSuchElementException) {
            throw noSuchElementException;
        } catch (Exception e) {
            throw new BadUserInformationException();
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void givePointsForExam() {
        Iterable<Exam> exams = examRepository.findAll();
        Optional<LoyaltyProgram> loyaltyProgramOptional = loyaltyProgramRepository.findById(LoyaltyProgram.defaultId);
        if (loyaltyProgramOptional.isPresent()) {
            LoyaltyProgram loyaltyProgram = loyaltyProgramOptional.get();
            exams.forEach(exam -> {
                if (exam.getStatus() == ExamStatus.EXAM_DONE_POINTS_NOT_GIVEN) {
                    Patient patient = exam.getPatient();
                    if (patient != null) {
                        int points = loyaltyProgram.getExamPoints();
                        logger.info("Points:  {}", points);
                        patient.addLoyaltyPoints(points);
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        exam.setStatus(ExamStatus.EXAM_DONE_POINTS_GIVEN);
                        examRepository.save(exam);
                        userRepository.save(patient);
                        logger.info("{} {} points given to {} id: {} for exam {}",
                                timestamp, points, patient.getName(), patient.getId(), exam.getId());
                    }
                }
            });
        }
    }

    @Override
    public LoyaltyProgramDto getLoyaltyProgramDto() {
        Optional<LoyaltyProgram> loyaltyOptional = loyaltyProgramRepository.findById(LoyaltyProgram.defaultId);
        if (loyaltyOptional.isEmpty()) {
            return null;
        }
        LoyaltyProgram loyaltyProgram = loyaltyOptional.get();
        LoyaltyProgramDto loyaltyProgramDto =
                new LoyaltyProgramDto(loyaltyProgram.getSilverMinimumPoints(), loyaltyProgram.getSilverDiscount(),
                        loyaltyProgram.getGoldMinimumPoints(), loyaltyProgram.getGoldDiscount(), loyaltyProgram.getExamPoints());
        return loyaltyProgramDto;
    }
}
