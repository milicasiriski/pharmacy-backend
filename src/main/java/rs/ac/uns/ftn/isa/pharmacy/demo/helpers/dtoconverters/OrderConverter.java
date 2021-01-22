package rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderResponseDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public interface OrderConverter {

    default List<OrderResponseDto> createResponse(List<Order> orders) {
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
}
