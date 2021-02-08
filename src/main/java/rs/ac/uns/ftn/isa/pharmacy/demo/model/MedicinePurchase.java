package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Map;

@Entity
@Table(name = "medicine_purchase")
public class MedicinePurchase {

    @Id
    @SequenceGenerator(name = "medicine_purchase_sequence_generator", sequenceName = "medicine_purchase_sequence", initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_purchase_sequence_generator")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "purchase_medicine_mapping", joinColumns = @JoinColumn(name = "purchase_id"))
    @MapKeyJoinColumn(name = "medicine_id", referencedColumnName = "id")
    @Column(name = "medicine_amount")
    private Map<Medicine, Integer> medicineAmount;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "purchase_date")
    private Calendar purchaseDate;

    @Column(name = "price")
    private Long price;
}
