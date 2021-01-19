package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void save(OrderDto orderDto);
    List<Order> getOrders();
}
