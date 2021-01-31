package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PharmacyDoesNotContainMedicineException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicinesBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemResponseDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PriceListService;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PriceListServiceImpl implements PriceListService {

    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final PharmacyService pharmacyService;

    @Autowired
    public PriceListServiceImpl(PharmacyRepository pharmacyRepository, MedicineRepository medicineRepository, PharmacyService pharmacyService) {
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
        this.pharmacyService = pharmacyService;
    }

    @Override
    public void updatePriceList(PriceListItemDto priceListItemDto) throws EntityNotFoundException {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());

        MedicinePriceListItem medicinePriceListItem = createMedicinePriceListItem(priceListItemDto);
        Medicine medicine = getMedicineById(priceListItemDto.getMedicineId());

        updatePharmacy(pharmacy, medicinePriceListItem, medicine);
    }

    @Override
    public List<PriceListItemResponseDto> collectCurrentMedicinePrices() {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyService.findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());

        Map<Medicine, MedicineStatus> medicines = pharmacy.getMedicine();
        List<PriceListItemResponseDto> priceItems = new ArrayList<>();

        pharmacy.getMedicine().keySet().forEach(medicine -> {
            List<MedicinePriceListItem> prices = medicines.get(medicine).getPrices();
            PriceListItemResponseDto priceListItem = currentPriceListItem(prices);
            MedicinesBasicInfoDto medicinesBasicInfoDto = new MedicinesBasicInfoDto(medicine.getName(), medicine.getForm().label, medicine.getId(), medicine.getRatings());
            priceListItem.setMedicineInfo(medicinesBasicInfoDto);
            priceItems.add(priceListItem);
        });
        return priceItems;
    }

    private void updatePharmacy(Pharmacy pharmacy, MedicinePriceListItem medicinePriceListItem, Medicine medicine) {
        if (pharmacy.getMedicine().containsKey(medicine)) {
            pharmacy.getMedicine().get(medicine).getPrices().add(medicinePriceListItem);
            pharmacyRepository.save(pharmacy);
        } else {
            throw new PharmacyDoesNotContainMedicineException();
        }
    }

    private MedicinePriceListItem createMedicinePriceListItem(PriceListItemDto priceListItemDto) {
        Calendar from = Calendar.getInstance();
        from.setTime(priceListItemDto.getFrom());

        Calendar to = Calendar.getInstance();
        to.setTime(priceListItemDto.getTo());

        TimeInterval timeInterval = new TimeInterval(from, to);
        return new MedicinePriceListItem(timeInterval, priceListItemDto.getPrice());
    }

    private Medicine getMedicineById(Long medicineId) throws EntityNotFoundException {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        if (optionalMedicine.isPresent()) {
            return optionalMedicine.get();
        } else {
            throw new EntityNotFoundException();
        }
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
