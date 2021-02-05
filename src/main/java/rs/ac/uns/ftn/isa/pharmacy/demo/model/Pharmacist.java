package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@DiscriminatorValue("PHARMACIST")
public class Pharmacist extends User {

    private transient final String administrationRole = "ROLE_PHARMACIST";

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @Column(name = "rating")
    private double rating;

    @ElementCollection
    @CollectionTable(name = "pharmacist_shift",
            joinColumns = {@JoinColumn(name = "pharmacist_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "day_of_week")
    private Map<DaysOfWeek, TimeInterval> shifts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacist_id")
    private List<Exam> exams;

    public Pharmacist() {
        super();
    }

    public Pharmacist(String email, String password, String name, String surname, double rating) {
        super(email, password, name, surname, new Address());
        this.rating = rating;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Map<DaysOfWeek, TimeInterval> getShifts() {
        return shifts;
    }

    public void setShifts(Map<DaysOfWeek, TimeInterval> shifts) {
        this.shifts = shifts;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pharmacist that = (Pharmacist) o;
        return Objects.equals(administrationRole, that.administrationRole) &&
                Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administrationRole, pharmacy);
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "administrationRole='" + administrationRole + '\'' +
                ", pharmacies=" + pharmacy +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", name=" + name +
                ", enabled=" + enabled +
                '}';
    }
}
