package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OrderHasOfferException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OtherPharmacyAdminCreatedOrderException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.OfferRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.OrderRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OrderService;

import java.util.*;
import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MedicineRepository medicineRepository;
    private final PharmacyRepository pharmacyRepository;
    private final OfferRepository offerRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MedicineRepository medicineRepository, PharmacyRepository pharmacyRepository, OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.medicineRepository = medicineRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void save(OrderDto orderDto) {
        HashMap<Medicine, Integer> medicineAmount = convertMedicineOrderDtoToMedicineOrder(orderDto);
        Calendar deadline = Calendar.getInstance();
        deadline.setTime(orderDto.getDeadline());
        Order order = new Order(medicineAmount, deadline, ((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        orderRepository.save(order);
    }

    @Override
    public List<OrderForOfferDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return convertToOrdersDto(orders);
    }

    private List<OrderForOfferDto> convertToOrdersDto(List<Order> orders) {
        List<OrderForOfferDto> ordersDto = new ArrayList<>();
        for (Order order : orders) {
            List<MedicineAmountForOfferDto> medicineAmount = new ArrayList<>();
            for (Medicine medicine : order.getMedicineAmount().keySet()) {
                MedicineDto medicineDto = new MedicineDto(medicine.getUuid(), medicine.getName(), medicine.getForm());
                medicineAmount.add(new MedicineAmountForOfferDto(medicineDto, order.getMedicineAmount().get(medicine)));
            }
            ordersDto.add(new OrderForOfferDto(medicineAmount, order.getDeadline().getTime(), order.getId()));
        }
        return ordersDto;
    }

    @Override
    public List<OrderForOfferDto> getNotOfferedOrders() {
        List<Order> orders = new ArrayList<>();
        User user;
        try {
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = (User) o;
            Supplier supplier = (Supplier) user;
            Iterable<Offer> offers = offerRepository.findBySupplierId(supplier.getId());
            Set<Order> offeredOrders = new HashSet<>();
            offers.forEach(offer -> offeredOrders.add(offer.getOrder()));
            orderRepository.findAll().forEach(order -> {
                if (!offeredOrders.contains(order)) {
                    orders.add(order);
                }
            });
            return convertToOrdersDto(orders);
        } catch (Exception e) {
            throw new BadUserInformationException();
        }
    }

    @Override
    public List<OrderResponseDto> getOrdersByPharmacy() {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long pharmacyId = pharmacyAdmin.getPharmacy().getId();
        return convertOrdersToOrderResponse(orderRepository.getOrdersByPharmacy(pharmacyId));
    }

    @Override
    public void deleteOrder(long orderId) throws EntityNotFoundException, OtherPharmacyAdminCreatedOrderException, OrderHasOfferException {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new EntityNotFoundException();
        }

        List<Offer> offers = offerRepository.findOffersByOrder(orderId);
        if (!offers.isEmpty()) {
            throw new OrderHasOfferException();
        }

        if (order.getPharmacyAdmin().getId().equals(pharmacyAdmin.getId())) {
            orderRepository.delete(order);
        } else {
            throw new OtherPharmacyAdminCreatedOrderException();
        }
    }

    private List<OrderResponseDto> convertOrdersToOrderResponse(List<Order> orders) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<OrderResponseDto> ordersDto = new ArrayList<>();
        orders.forEach(order -> {
            List<MedicineAmountDto> medicineAmounts = new ArrayList<>();
            order.getMedicineAmount().forEach((medicine, amount) -> {
                MedicineAmountDto medicineAmount = new MedicineAmountDto(medicine.getName(), amount);
                medicineAmounts.add(medicineAmount);
            });

            String strDate = dateFormat.format(order.getDeadline().getTime());
            OrderResponseDto orderResponseDto = new OrderResponseDto(strDate, medicineAmounts,
                    ((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), order.getId());
            ordersDto.add(orderResponseDto);
        });
        return ordersDto;
    }

    private HashMap<Medicine, Integer> convertMedicineOrderDtoToMedicineOrder(OrderDto orderDto) {
        HashMap<Long, Integer> medicineAmount = orderDto.getOrderItems();
        HashMap<Medicine, Integer> newMedicineAmount = new HashMap<>();
        List<Medicine> medicines = new ArrayList<>();

        medicineAmount.keySet().forEach(medicineId -> {
            Medicine medicine = findMedicineById(medicineId);
            newMedicineAmount.put(medicine, medicineAmount.get(medicineId));
            medicines.add(medicine);
        });

        addMedicineIfDoesntExist(medicines);
        return newMedicineAmount;
    }

    private void addMedicineIfDoesntExist(List<Medicine> medicines) {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long pharmacyId = pharmacyAdmin.getPharmacy().getId();
        Pharmacy pharmacy = findPharmacyById(pharmacyId);
        Map<Medicine, MedicineStatus> medicinesMap = pharmacy.getMedicine();

        medicines.forEach(medicine -> {
            if (!medicinesMap.containsKey(medicine)) {
                MedicineStatus medicineStatus = new MedicineStatus(0, new ArrayList<>());
                medicinesMap.put(medicine, medicineStatus);
            }
        });

        pharmacyRepository.save(pharmacy);
    }

    private Medicine findMedicineById(Long medicineId) throws EntityNotFoundException {
        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
        if (medicine == null) {
            throw new EntityNotFoundException();
        } else {
            return medicine;
        }
    }

    private Pharmacy findPharmacyById(Long pharmacyId) throws EntityNotFoundException {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElse(null);
        if (pharmacy == null) {
            throw new EntityNotFoundException();
        } else {
            return pharmacy;
        }
    }
}
