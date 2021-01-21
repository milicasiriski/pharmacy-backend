package rs.ac.uns.ftn.isa.pharmacy.demo.helpers;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public interface DtoResponseConverters {

    default List<DermatologistDto> createDermatologistsResponseDtoFromDermatologists(List<Dermatologist> dermatologists) {
        List<DermatologistDto> dermatologistsDto = new ArrayList<>();
        dermatologists.forEach(dermatologist -> {
                    List<Pharmacy> pharmacies = dermatologist.getPharmacies();
                    List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

                    findAllRelevantPharmacies(pharmacies, dtoPharmacies);
                    DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.75, dtoPharmacies);
                    dermatologistsDto.add(dermatologistDto);
                }
        );
        return dermatologistsDto;
    }

    default List<PharmacistDto> createPharmacistsResponseDtoFromPharmacists(List<Pharmacist> pharmacists) {
        List<PharmacistDto> pharmacistsDto = new ArrayList<>();
        pharmacists.forEach(pharmacist -> {
                    List<Pharmacy> pharmacies = pharmacist.getPharmacies();
                    List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

                    findAllRelevantPharmacies(pharmacies, dtoPharmacies);
                    PharmacistDto pharmacistDto = new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(), 4.75, dtoPharmacies);
                    pharmacistsDto.add(pharmacistDto);
                }
        );
        return pharmacistsDto;
    }

    default List<OrderResponseDto> createOrderResponseDtoFromOrder(List<Order> orders) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<OrderResponseDto> ordersDto = new ArrayList<>();
        orders.forEach(order -> {
            List<MedicineAmountDto> medicineAmounts = new ArrayList<>();
            order.getMedicineAmount().forEach((medicine, amount) -> {
                MedicineAmountDto medicineAmount = new MedicineAmountDto(medicine.getName(), amount);
                medicineAmounts.add(medicineAmount);
            });

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setMedicineAmount(medicineAmounts);
            String strDate = dateFormat.format(order.getDeadline().getTime());
            orderResponseDto.setDeadlineString(strDate);
            ordersDto.add(orderResponseDto);
        });
        return ordersDto;
    }

    private void findAllRelevantPharmacies(List<Pharmacy> pharmacies, List<PharmacyNameAndAddressDto> dtoPharmacies) {
        pharmacies.forEach(pharmacy -> {
            PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress());
            dtoPharmacies.add(pharmacyNameAndAddressDto);
        });
    }
}
