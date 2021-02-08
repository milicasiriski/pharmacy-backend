package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ComplaintAnswerDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.ComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacistDto;

import java.util.List;

public interface ComplaintService {
    List<DermatologistDto> getDermatologists();

    List<PharmacistDto> getPharmacists();

    List<ComplaintDto> getUnresolvedComplains();

    void makeAComplaint(ComplaintDto complaintDto);

    void resolveComplaint(ComplaintAnswerDto dto) throws Exception;
}
