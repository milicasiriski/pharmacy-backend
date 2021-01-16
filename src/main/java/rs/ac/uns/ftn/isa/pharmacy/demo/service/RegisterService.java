package rs.ac.uns.ftn.isa.pharmacy.demo.service;

public interface RegisterService<DTO, USER> {

    USER save(DTO dto);

    USER findByEmail(String email);

}
