package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.EPrescriptionDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.QRResultDto;

import java.util.List;

public interface QRService {
    List<QRResultDto> findPharmacies(EPrescriptionDto dto);

    void buy(QRResultDto dto) throws Exception;
}
