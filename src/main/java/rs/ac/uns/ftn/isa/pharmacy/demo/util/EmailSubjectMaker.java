package rs.ac.uns.ftn.isa.pharmacy.demo.util;

public class EmailSubjectMaker {

    public String makeActivationHtml(String link) {

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
                "            Your activation link is <a href=\"" +
                link +
                "\">here</a>.\n" +
                "<br> Thank you for using our application!"  +
                "        </p>\n" +
                "    </body>\n" +
                "</html>";
    }

}
