package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters.OrderConverter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderResponseDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController implements OrderConverter {

    @Qualifier("orderServiceImpl")
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }

    @GetMapping("/getOrdersByPharmacy")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    public ResponseEntity<List<OrderResponseDto>> getOrdersByPharmacy() {
       return ResponseEntity.ok(orderService.getOrdersByPharmacy());
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/notOffered")
    public ResponseEntity<List<OrderDto>> getNotOfferedOrders() {
        List<OrderDto> orders = orderService.getNotOfferedOrders();
        return ResponseEntity.ok(orders);
    }
}
