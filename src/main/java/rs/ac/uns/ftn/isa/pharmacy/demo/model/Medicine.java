package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

    @Id
    @SequenceGenerator(name = "medicine_sequence_generator", sequenceName = "medicine_sequence", initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_sequence_generator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "form")
    private String form;

    public Medicine() {
    }

    public Medicine(String name, String description, String form) {
        this.name = name;
        this.description = description;
        this.form = form;
    }

    public Medicine(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}