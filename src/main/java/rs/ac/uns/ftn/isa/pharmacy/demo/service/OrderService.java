package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    void save(OrderDto orderDto);

    List<OrderDto> getOrders();

    List<OrderDto> getNotOfferedOrders();

    List<OrderResponseDto> getOrdersByPharmacy();
}
