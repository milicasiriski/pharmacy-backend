package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PatientShoppingEmailParams;

public class PatientsShoppingConfirmMailFormatter implements MailFormatter<PatientShoppingEmailParams> {
    @Override
    public String getText(PatientShoppingEmailParams params) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <title>PharmacyManager</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>\n" +
                "            ®™PharmacyManager\n" +
                "        </h1>\n" +
                "        <hr>\n" +
                "        <p>\n" +
                " Dear, " + params.getUserName() + ", \n" +
                "             your shopping at " + params.getPharmacyName() + " is confirmed.\n" +
                "Total bill is: " + params.getBill() + "€.\n" +
                "<br> Thank you for using our application!" +
                "        </p>\n" +
                "    </body>\n" +
                "</html>";
    }

    @Override
    public String getSubject() {
        return "Medicines shopping confirmed!";
    }
}
