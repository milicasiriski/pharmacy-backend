package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @SequenceGenerator(name = "medicine_sequence_generator", sequenceName = "medicine_sequence", initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_sequence_generator")
    private Long id;

    public Medicine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
