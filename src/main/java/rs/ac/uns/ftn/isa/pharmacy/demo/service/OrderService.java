package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderForOfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    void save(OrderDto orderDto);

    List<OrderForOfferDto> getOrders();

    List<OrderForOfferDto> getNotOfferedOrders();

    List<OrderResponseDto> getOrdersByPharmacy();

    void deleteOrder(long orderId);
}
