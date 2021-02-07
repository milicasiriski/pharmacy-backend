package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class PatientShoppingEmailParams {

    String userName;
    String pharmacyName;
    String bill;

    public PatientShoppingEmailParams(String userName, String pharmacyName, String bill) {
        this.userName = userName;
        this.pharmacyName = pharmacyName;
        this.bill = bill;
    }

    public PatientShoppingEmailParams() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
