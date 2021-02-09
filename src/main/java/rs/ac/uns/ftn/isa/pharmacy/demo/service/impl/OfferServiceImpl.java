package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.MailService;
import rs.ac.uns.ftn.isa.pharmacy.demo.mail.OfferMailFormatter;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineAmountDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.OfferStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OfferService;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;
    private final PharmacyRepository pharmacyRepository;
    private final SupplierRepository supplierRepository;
    private final MailService<Boolean> mailService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, OrderRepository orderRepository, UserRepository userRepository,
                            MedicineRepository medicineRepository, PharmacyRepository pharmacyRepository, SupplierRepository supplierRepository,
                            MailService<Boolean> mailService) {
        this.offerRepository = offerRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.medicineRepository = medicineRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.supplierRepository = supplierRepository;
        this.mailService = mailService;
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

    @Override
    public List<List<OfferDto>> getAllOffersByOrders() {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        List<Long> orderIds = orderRepository.getOrderIdsByPharmacy(pharmacy.getId());

        return extractRelevantOffers(orderIds);
    }

    @Override
    public void acceptOffer(Long offerId) throws EntityNotFoundException, MessagingException, OtherPharmacyAdminCreatedOrderException, OfferDeadlineHasNotExpiredException {
        Offer offer = offerRepository.findById(offerId).orElse(null);
        List<Supplier> suppliers = new ArrayList<>();

        if (offer == null) {
            throw new EntityNotFoundException();
        }
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderRepository.findById(offer.getOrder().getId()).orElse(null);
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyAdmin.getPharmacy().getId()).orElse(null);

        if (order == null || pharmacy == null) {
            throw new EntityNotFoundException();
        }

        if (order.getDeadline().after(Calendar.getInstance())) {
            throw new OfferDeadlineHasNotExpiredException();
        }

        if (order.getPharmacyAdmin().getId().equals(pharmacyAdmin.getId())) {
            updateOfferStatus(offer);
            updateOrderStatus(order);
            Map<Medicine, Integer> medicineAmount = order.getMedicineAmount();
            updateMedicineStatus(pharmacy, medicineAmount);
            sendEmail(offerId, suppliers, order);

        } else {
            throw new OtherPharmacyAdminCreatedOrderException();
        }
    }

    private void sendEmail(Long offerId, List<Supplier> suppliers, Order order) throws MessagingException {
        List<Long> supplierIds = offerRepository.getSupplierIds(order.getId());
        Long acceptedOfferSupplierId = offerRepository.getAcceptedOfferSupplierId(offerId);

        for (Long id : supplierIds) {
            Supplier supplier = supplierRepository.findById(id).orElse(null);
            if (supplier == null) {
                throw new EntityNotFoundException();
            } else {
                suppliers.add(supplier);
            }
        }

        Supplier supplier = supplierRepository.findById(acceptedOfferSupplierId).orElse(null);
        if (supplier == null) {
            throw new EntityNotFoundException();
        } else {
            mailService.sendMail(supplier.getEmail(), true, new OfferMailFormatter());
        }

        for (Supplier s : suppliers) {
            if (!s.equals(supplier)) {
                mailService.sendMail(supplier.getEmail(), false, new OfferMailFormatter());
            }
        }
    }

    private void updateOrderStatus(Order order) {
        order.setOfferAccepted(true);
        orderRepository.save(order);
    }

    private void updateMedicineStatus(Pharmacy pharmacy, Map<Medicine, Integer> medicineAmount) throws EntityNotFoundException {
        pharmacy.addMedicinesOnStock(medicineAmount);
        pharmacyRepository.save(pharmacy);
    }

    private void updateOfferStatus(Offer offer) {
        offer.setStatus(OfferStatus.ACCEPTED);
        List<Offer> offers = offerRepository.findOffersByOrder(offer.getOrder().getId());
        offers.forEach(o -> {
            if (!o.equals(offer)) {
                o.setStatus(OfferStatus.REJECTED);
                offerRepository.save(o);
            }
        });
        offerRepository.save(offer);
    }

    private List<List<OfferDto>> extractRelevantOffers(List<Long> orderIds) {
        List<List<OfferDto>> allOffers = new ArrayList<>();
        orderIds.forEach(id -> {
            List<Offer> offers;
            List<OfferDto> offerDtos = new ArrayList<>();
            offers = offerRepository.findOffersByOrder(id);
            offers.forEach(offer -> {
                OfferDto offerDto = new OfferDto(offer.getShippingDays(), offer.getPrice(),
                        offer.getOrder().getId(), offer.getStatus(), offer.getId(), offer.getOrder().getDeadline().getTime());
                offerDtos.add(offerDto);
            });

            allOffers.add(offerDtos);
        });
        return allOffers;
    }

    private boolean canUpdate(Offer offer) {
        return offer.getOrder().getDeadline().getTimeInMillis() > System.currentTimeMillis() && offer.getStatus() != OfferStatus.ACCEPTED;
    }

    private Map<Medicine, Integer> refreshMedicineAmount(Map<Medicine, Integer> orderMedicineAmount, Long supplierId) throws NoMedicineFoundException {
        Map<Medicine, Integer> newSuppliersMedicineAmount = new HashMap<>();
        for (Map.Entry<Medicine, Integer> m : orderMedicineAmount.entrySet()) {
            Medicine medicine = m.getKey();
            int orderAmount = m.getValue();
            Integer supplierAmount = offerRepository.findMedicineAmountBySupplierId(supplierId, medicine.getId());
            if (supplierAmount == null) {
                throw new NoMedicineFoundException();
            }
            if (orderAmount <= supplierAmount) {
                newSuppliersMedicineAmount.put(medicine, supplierAmount - orderAmount);
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
