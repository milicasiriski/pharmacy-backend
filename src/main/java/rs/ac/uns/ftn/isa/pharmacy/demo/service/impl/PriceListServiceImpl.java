package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PharmacyDoesNotContainMedicineException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PriceListService;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Optional;

@Service
public class PriceListServiceImpl implements PriceListService {

    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;

    @Autowired
    public PriceListServiceImpl(PharmacyRepository pharmacyRepository, MedicineRepository medicineRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void updatePriceList(PriceListItemDto priceListItemDto) throws EntityNotFoundException {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());

        MedicinePriceListItem medicinePriceListItem = createMedicinePriceListItem(priceListItemDto);
        Medicine medicine = getMedicineById(priceListItemDto.getMedicineId());

        updatePharmacy(pharmacy, medicinePriceListItem, medicine);
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
}
