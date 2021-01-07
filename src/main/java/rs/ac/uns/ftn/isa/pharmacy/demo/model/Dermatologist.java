package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("DERMATOLOGIST")
public class Dermatologist extends User {

    public Dermatologist() {
        super();
    }

    public Dermatologist(String email, String password) {
        super(email, password);
    }
}
