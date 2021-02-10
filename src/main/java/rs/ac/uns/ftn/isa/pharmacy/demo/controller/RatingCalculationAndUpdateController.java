package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.RatingCalculationAndUpdateService;

@Controller
@RequestMapping(value = "/ratingCalculation")
public class RatingCalculationAndUpdateController {
    private final RatingCalculationAndUpdateService ratingCalculationAndUpdateService;

    public RatingCalculationAndUpdateController(RatingCalculationAndUpdateService ratingCalculationAndUpdateService) {
        this.ratingCalculationAndUpdateService = ratingCalculationAndUpdateService;
    }

    @Scheduled(cron = "${ratingCalculationAndUpdateMedicine.cron}")
    public void executeMedicineRatingCalculations() {
        ratingCalculationAndUpdateService.calculateAndUpdateMedicineRatings();
    }

    @Scheduled(cron = "${ratingCalculationAndUpdateDermatologists.cron}")
    public void executeMedicineDermatologistCalculations() {
        ratingCalculationAndUpdateService.calculateAndUpdateDermatologistRatings();
    }

    @Scheduled(cron = "${ratingCalculationAndUpdatePharmacists.cron}")
    public void executePharmacistRatingCalculations() {
        ratingCalculationAndUpdateService.calculateAndUpdatePharmacistRatings();
    }

    @Scheduled(cron = "${ratingCalculationAndUpdatePharmacies.cron}")
    public void executePharmacyRatingCalculations() {
        ratingCalculationAndUpdateService.calculateAndUpdatePharmacyRatings();
    }
}
