package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PriceListItemResponseDto;

import java.util.List;

public interface PriceListService {
    void updatePriceList(PriceListItemDto priceListItemDto);
    List<PriceListItemResponseDto> collectCurrentMedicinePrices();
}
