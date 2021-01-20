package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientDTO;

public interface RegisterService<DTO, USER>{

    USER findByEmail(String email);

}
