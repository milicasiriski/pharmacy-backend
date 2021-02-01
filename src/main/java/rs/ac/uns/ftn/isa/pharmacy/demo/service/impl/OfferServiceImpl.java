package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.BadUserInformationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.NoMedicineFoundException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.OrderException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Medicine;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Order;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Supplier;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.OfferDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.OfferStatus;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.OfferRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.OrderRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.UserRepository;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.OfferService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Offer addNewOffer(OfferDto dto) {
        Supplier supplier;
        try {
            supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Offer offer = dtoToOffer(dto);
            offer.setStatus(OfferStatus.WAITING);
            Order order = offer.getOrder();
            supplier.setMedicineAmount(refreshMedicineAmount(order.getMedicineAmount(), supplier.getMedicineAmount()));
            offer = offerRepository.save(offer);
            supplier.getOffers().add(offer);
            userRepository.save(supplier);
            return offer;
        } catch (NoMedicineFoundException noMedicineFoundException) {
            throw noMedicineFoundException;
        } catch (OrderException orderException) {
            throw orderException;
        } catch (Exception e) {
            throw new BadUserInformationException();
        }

    }

    private Map<Medicine, Integer> refreshMedicineAmount(Map<Medicine, Integer> orderMedicineAmount, Map<Medicine, Integer> supplierMedicineAmount) throws NoMedicineFoundException {
        Map<Medicine, Integer> newSuppliersMedicineAmount = new HashMap<>();
        for (Medicine m : orderMedicineAmount.keySet()) {
            if (orderMedicineAmount.get(m) > supplierMedicineAmount.get(m)) {
                newSuppliersMedicineAmount.put(m, orderMedicineAmount.get(m) - supplierMedicineAmount.get(m));
            } else {
                throw new NoMedicineFoundException();
            }
        }
        return newSuppliersMedicineAmount;
    }

    private Offer dtoToOffer(OfferDto dto) {
        Optional<Order> order = orderRepository.findById(dto.getOrder().getId());
        if (order.isEmpty()) {
            throw new OrderException();
        }
        return new Offer(order.get(), dto.getPrice(), dto.getShippingDays(), dto.getStatus());
    }
}
