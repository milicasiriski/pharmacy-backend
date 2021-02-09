package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

public class OfferMailFormatter implements MailFormatter<Boolean> {
    private static final String ACCEPTED_TEXT = "Your offer has been accepted!";
    private static final String DECLINED_TEXT = "Your offer has been rejected!";

    @Override
    public String getText(Boolean isAccepted) {
        String message = "";
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
                "\t\t" + message + "<br><br>\n" +
                "\t\tThank you for using our application!\n" +
                "\t</p>\n" +
                "\t\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    public String getSubject() {
        return "Order/Offer";
    }
}
