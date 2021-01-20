package rs.ac.uns.ftn.isa.pharmacy.demo.helpers;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderResponseDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class DtoResponseConverters {

    public static void createDermatologistsResponseDtoFromDermatologists(List<Dermatologist> dermatologists, List<DermatologistDto> dermatologistsDto) {
        dermatologists.forEach(dermatologist -> {
                    List<Pharmacy> pharmacies = dermatologist.getPharmacies();
                    List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();
                    pharmacies.forEach(pharmacy -> {
                        PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress());
                        dtoPharmacies.add(pharmacyNameAndAddressDto);
                    });

                    DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.75, dtoPharmacies);
                    dermatologistsDto.add(dermatologistDto);
                }
        );
    }

    public static void createOrderResponseDtoFromOrder(List<Order> orders, List<OrderResponseDto> ordersDto) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
    }
}
