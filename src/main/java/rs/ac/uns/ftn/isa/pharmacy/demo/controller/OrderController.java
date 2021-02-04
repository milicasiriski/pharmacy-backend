package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OrderHasOfferException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OtherPharmacyAdminCreatedOrderException;
import rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters.OrderConverter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderForOfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderResponseDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OrderService;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<List<OrderForOfferDto>> getOrders() {
        List<OrderForOfferDto> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @PreAuthorize("hasRole('ROLE_SUPPLIER')") // NOSONAR the focus of this project is not on web security
    @GetMapping("/notOffered")
    public ResponseEntity<List<OrderForOfferDto>> getNotOfferedOrders() {
        List<OrderForOfferDto> orders = orderService.getNotOfferedOrders();
        return ResponseEntity.ok(orders);
    }

    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMINISTRATOR')") // NOSONAR the focus of this project is not on web security
    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Order does not exist!", HttpStatus.BAD_REQUEST);
        } catch (OrderHasOfferException e) {
            return new ResponseEntity<>("Order already has offer!", HttpStatus.BAD_REQUEST);
        } catch (OtherPharmacyAdminCreatedOrderException e) {
            return new ResponseEntity<>("Someone else created this order!", HttpStatus.BAD_REQUEST);
        }
    }

}
