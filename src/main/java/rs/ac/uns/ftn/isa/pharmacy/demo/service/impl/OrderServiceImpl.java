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
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MedicineRepository medicineRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MedicineRepository medicineRepository) {
        this.orderRepository = orderRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void save(OrderDto orderDto) {

        HashMap<Medicine, Integer> medicineAmount = getMedicineOrderMap(orderDto);
        Calendar deadline = Calendar.getInstance();
        deadline.setTime(orderDto.getDeadline());
        Order order = new Order(medicineAmount, deadline);
        orderRepository.save(order);
    }

    private HashMap<Medicine, Integer> getMedicineOrderMap(OrderDto orderDto) {

        HashMap<String, Integer> medicineAmount = orderDto.getOrderItems();
        HashMap<Medicine, Integer> newMedicineAmount = new HashMap<>();

        for (Map.Entry<String, Integer> medicineOrder : medicineAmount.entrySet()) {
            Medicine medicine = medicineRepository.findByName(medicineOrder.getKey());
            if (medicine == null) {
                medicine = new Medicine(medicineOrder.getKey());
                medicineRepository.save(medicine);
            }

            newMedicineAmount.put(medicine, medicineOrder.getValue());
        }
        return newMedicineAmount;
    }
}
