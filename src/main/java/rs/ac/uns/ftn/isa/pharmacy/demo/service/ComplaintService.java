package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;

import java.util.List;

public interface ComplaintService {
    List<DermatologistDto> getDermatologists();

    List<PharmacistDto> getPharmacists();

    List<ComplaintDto> getUnresolvedComplains();

    void makeAComplaint(ComplaintDto complaintDto);

    void resolveComplaint(ComplaintAnswerDto dto) throws Exception;

    List<PharmacyDto> getPharmacies();
}
