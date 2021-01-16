package rs.ac.uns.ftn.isa.pharmacy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OrderService;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public void createOrder(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto.getName());
        System.out.println(orderDto.getAmount());
        orderService.save(orderDto);
    }

}
