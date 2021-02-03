package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadRequestException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OrderException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Supplier;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.OfferStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.MedicineRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.OfferRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.OrderRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OfferService;

import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, OrderRepository orderRepository, UserRepository userRepository, MedicineRepository medicineRepository) {
        this.offerRepository = offerRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Offer addNewOffer(OfferDto dto) throws NoMedicineFoundException, OrderException {
        Supplier supplier;
        try {
            if (dto.getShippingDays() < 1 || dto.getPrice() < 1) {
                throw new BadRequestException();
            }
            supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Offer offer = dtoToOffer(dto);
            offer.setStatus(OfferStatus.WAITING);
            Order order = offer.getOrder();
            Map<Medicine, Integer> map = getMedicineAmount(supplier.getId());
            map.putAll(refreshMedicineAmount(order.getMedicineAmount(), supplier.getId()));
            supplier.setMedicineAmount(map);
            offer = offerRepository.save(offer);
            Set<Offer> suppliersOffers = new HashSet<>((Collection) offerRepository.findBySupplierId(supplier.getId()));
            suppliersOffers.add(offer);
            supplier.setOffers(suppliersOffers);
            userRepository.save(supplier);
            return offer;
        } catch (NoMedicineFoundException | OrderException noMedicineFoundException) {
            throw noMedicineFoundException;
        } catch (Exception e) {
            throw new BadUserInformationException();
        }
    }

    @Override
    public List<MedicineAmountDto> getMedicinesAmount() throws BadUserInformationException {
        Supplier supplier;
        try {
            List<MedicineAmountDto> medicinesAmountDto = new ArrayList<>();
            supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Map<Medicine, Integer> map = getMedicineAmount(supplier.getId());
            map.keySet().forEach(medicine ->
                    medicinesAmountDto.add(new MedicineAmountDto(medicine.getName(), medicine.getUuid(), map.get(medicine))));
            return medicinesAmountDto;
        } catch (Exception e) {
            throw new BadUserInformationException();
        }
    }

    @Override
    public List<OfferDto> getAllOffers() {
        Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<Offer> offers = offerRepository.findBySupplierId(supplier.getId());
        List<OfferDto> offerDtos = new ArrayList<>();
        offers.forEach(offer -> offerDtos.add(new OfferDto(offer.getShippingDays(), offer.getPrice(), offer.getOrder().getId(), offer.getStatus(), offer.getId(), offer.getOrder().getDeadline().getTime())));
        return offerDtos;
    }

    @Override
    public Offer updateOffer(OfferDto offerDto) {
        Optional<Offer> o = offerRepository.findById(offerDto.getOfferId());
        if (o.isEmpty()) {
            throw new BadRequestException();
        } else {
            Offer offer = o.get();
            if (canUpdate(offer)) {
                offer.setPrice(offerDto.getPrice());
                offer.setShippingDays(offerDto.getShippingDays());
                return offerRepository.save(offer);
            } else {
                throw new BadRequestException();
            }
        }
    }

    private boolean canUpdate(Offer offer) {
        return offer.getOrder().getDeadline().getTimeInMillis() > System.currentTimeMillis() && offer.getStatus() != OfferStatus.ACCEPTED;
    }

    private Map<Medicine, Integer> refreshMedicineAmount(Map<Medicine, Integer> orderMedicineAmount, Long supplierId) throws NoMedicineFoundException {
        Map<Medicine, Integer> newSuppliersMedicineAmount = new HashMap<>();
        for (Medicine m : orderMedicineAmount.keySet()) {
            int orderAmount = orderMedicineAmount.get(m);
            Integer supplierAmount = offerRepository.findMedicineAmountBySupplierId(supplierId, m.getId());
            if (supplierAmount == null) {
                throw new NoMedicineFoundException();
            }
            if (orderAmount <= supplierAmount) {
                newSuppliersMedicineAmount.put(m, orderAmount - supplierAmount);
            } else {
                throw new NoMedicineFoundException();
            }
        }
        return newSuppliersMedicineAmount;
    }

    private Offer dtoToOffer(OfferDto dto) {
        Optional<Order> order = orderRepository.findById(dto.getOrderId());
        if (order.isEmpty()) {
            throw new OrderException();
        }
        return new Offer(order.get(), dto.getPrice(), dto.getShippingDays(), dto.getStatus());
    }

    private Map<Medicine, Integer> getMedicineAmount(Long supplierId) {
        List<Long> ids = offerRepository.findAllMedicineIdsBySupplierId(supplierId);
        Map<Medicine, Integer> map = new HashMap<>();
        ids.forEach(id -> {
            Optional<Medicine> m = medicineRepository.findById(id);
            if (m.isPresent()) {
                map.put(m.get(), offerRepository.findMedicineAmountBySupplierId(supplierId, id));
            } else {
                throw new BadRequestException();
            }
        });
        return map;
    }
}
