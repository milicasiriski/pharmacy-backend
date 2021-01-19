package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderResponseDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OrderService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Qualifier("orderServiceImpl")
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        List<Order> orders = orderService.getOrders();
        List<OrderResponseDto> ordersDto = new ArrayList<>();
        createOrderResponseDtoFromOrder(orders, ordersDto);
        return ResponseEntity.ok(ordersDto);
    }

    private void createOrderResponseDtoFromOrder(List<Order> orders, List<OrderResponseDto> ordersDto) {
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
