package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "medicine_search")
public class MedicineSearch {

    @Id
    @SequenceGenerator(name = "medicine_search_sequence_generator", sequenceName = "medicine_purchase_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_purchase_sequence_generator")
    private Long id;

    @Column(name = "medicine_id")
    private Long medicine_id;

    @Column(name = "pharmacy_id")
    private Double price;
}
