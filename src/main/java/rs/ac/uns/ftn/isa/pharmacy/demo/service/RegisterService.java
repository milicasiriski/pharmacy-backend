package rs.ac.uns.ftn.isa.pharmacy.demo.service;

public interface RegisterService<DTO, USER> {

    USER findByEmail(String email);

    boolean userExists(String email);
}
