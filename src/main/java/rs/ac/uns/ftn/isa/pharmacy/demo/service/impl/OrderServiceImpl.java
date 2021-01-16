package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OrderDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.OrderRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OrderService;

import java.util.Calendar;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private MedicineRepository medicineRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MedicineRepository medicineRepository) {
        this.orderRepository = orderRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void save(OrderDto orderDto) {

        // TODO: get medicine name
        Calendar deadline = Calendar.getInstance();
        //    deadline.setTime(orderDto.getDeadline());
        Order order = new Order(medicineRepository.save(new Medicine()), orderDto.getAmount(), deadline);
        orderRepository.save(order);
    }
}
