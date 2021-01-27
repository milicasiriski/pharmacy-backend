package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PromotionDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PromotionMailFormatter implements MailFormatter<PromotionDto> {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String getText(PromotionDto params) {
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
                "\t\t" + params.getNotificationMessage() + " And it's available from " +
                dateFormat.format(params.getFrom()) +
                " to " + dateFormat.format(params.getTo()) +
                "<br><br>\n" +
                "\t\tThank you for using our application!\n" +
                "\t</p>\n" +
                "\t\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    public String getSubject() {
        return "New promotion!";
    }
}
