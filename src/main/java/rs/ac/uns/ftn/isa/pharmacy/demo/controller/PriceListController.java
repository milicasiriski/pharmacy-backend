package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PharmacyDoesNotContainMedicineException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemResponseDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PriceListService;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/priceList", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceListController {

    private final PharmacyService pharmacyService;
    private final PriceListService priceListService;

    @Autowired
    public PriceListController(PharmacyService pharmacyService, PriceListService priceListService) {
        this.pharmacyService = pharmacyService;
        this.priceListService = priceListService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<List<PriceListItemResponseDto>> getPriceLists() {
        List<PriceListItemResponseDto> priceItems = collectRelevantMedicinePrices();
        return ResponseEntity.ok(priceItems);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<String> saveNewPrice(@RequestBody PriceListItemDto priceListItemDto) {
        try {
            priceListService.updatePriceList(priceListItemDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("There is no such medicine!", HttpStatus.BAD_REQUEST);
        } catch (PharmacyDoesNotContainMedicineException e) {
            return new ResponseEntity<>("Pharmacy doesnt have such medicine!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<PriceListItemResponseDto> collectRelevantMedicinePrices() {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyService.findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());

        Map<Medicine, MedicineStatus> medicines = pharmacy.getMedicine();
        List<PriceListItemResponseDto> priceItems = new ArrayList<>();

        pharmacy.getMedicine().keySet().forEach(medicine -> {
            List<MedicinePriceListItem> prices = medicines.get(medicine).getPrices();
            PriceListItemResponseDto priceListItem = currentPriceListItem(prices);
            priceListItem.setMedicine(medicine);
            priceItems.add(priceListItem);
        });
        return priceItems;
    }

    private PriceListItemResponseDto currentPriceListItem(List<MedicinePriceListItem> prices) {
        Calendar calendar = Calendar.getInstance();
        PriceListItemResponseDto priceListItemDto = new PriceListItemResponseDto();
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