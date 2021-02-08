package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

public class ComplaintMailFormatter implements MailFormatter<String> {
    @Override
    public String getText(String params) {
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
                params +

                "<hr><br><br> Thank you for using our application!" +
                "        </p>\n" +
                "    </body>\n" +
                "</html>";
    }

    @Override
    public String getSubject() {
        return "Complain answer.";
    }
}
