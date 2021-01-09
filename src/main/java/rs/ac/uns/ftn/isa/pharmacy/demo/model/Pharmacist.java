package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PHARMACIST")
public class Pharmacist extends User {

    public Pharmacist() {
        super();
    }

    public Pharmacist(String email, String password) {
        super();
    }
}
