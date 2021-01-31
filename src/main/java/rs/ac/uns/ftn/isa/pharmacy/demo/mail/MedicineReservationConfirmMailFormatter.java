package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.MedicineReservationEmailParams;

import java.text.SimpleDateFormat;

public class MedicineReservationConfirmMailFormatter implements MailFormatter<MedicineReservationEmailParams> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String getText(MedicineReservationEmailParams params) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "    <title>PharmacyManager</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<h1>\n" +
                "\t\t®™PharmacyManager\n" +
                "\t</h1>\n" +
                "\t<hr>\n" +
                "\t<p>\n" +
                "\t\tYour " + params.getMedicineName() + " reservation is confirmed! <br>\n" +
                "\t\tPlease make sure to pick it up by " + dateFormat.format(params.getDeadline()) +
                " in " + params.getPharmacyName() + ", " + params.getPharmacyAddress() + "<br><br>\n" +
                "\t\tUnique reservation number is: " + params.getUniqueReservationNumber() + "<br><br>\n" +
                "\t\tThank you for using our application!\n" +
                "\t</p>\n" +
                "\t\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    public String getSubject() {
        return "Medicine reservation confirmed!";
    }
}
