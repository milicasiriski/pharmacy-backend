package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.impl.PharmacyServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/priceList", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceListController {

    private final PharmacyServiceImpl pharmacyService;

    @Autowired
    public PriceListController(PharmacyServiceImpl pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')")
    public ResponseEntity<List<PriceListItemDto>> getPriceLists() {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<Pharmacy> pharmacies = pharmacyService.findAllPharmaciesByPharmacyAdmin(pharmacyAdmin.getId());
        List<PriceListItemDto> priceItems = new ArrayList<>();

        pharmacies.forEach(pharmacy -> {
            Map<Medicine, MedicineStatus> medicines = pharmacy.getMedicine();
            pharmacy.getMedicine().keySet().forEach(medicine -> {
                List<MedicinePriceListItem> prices = medicines.get(medicine).getPrices();
                PriceListItemDto priceListItem = currentPriceListItem(prices);
                priceItems.add(priceListItem);
            });
        });

        return ResponseEntity.ok(priceItems);
    }

    private PriceListItemDto currentPriceListItem(List<MedicinePriceListItem> prices) {
        Calendar calendar = Calendar.getInstance();
        PriceListItemDto priceListItemDto = new PriceListItemDto();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Optional<MedicinePriceListItem> currentPriceListItem = prices.stream().filter(price ->
                (calendar.compareTo(price.getTimeInterval().getStart()) >= 0 && calendar.compareTo(price.getTimeInterval().getEnd()) <= 0)).findAny();

        if (currentPriceListItem.isEmpty()) {
            priceListItemDto.setCurrentPrice(0.0);
        } else {
            priceListItemDto.setCurrentPrice(currentPriceListItem.get().getPrice());
            priceListItemDto.setStartDate(dateFormat.format(currentPriceListItem.get().getTimeInterval().getStart().getTime()));
            priceListItemDto.setEndDate(dateFormat.format(currentPriceListItem.get().getTimeInterval().getEnd().getTime()));
        }
        return priceListItemDto;
    }
}