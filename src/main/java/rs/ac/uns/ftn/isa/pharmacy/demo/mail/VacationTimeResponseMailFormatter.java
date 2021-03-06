package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.VacationTimeResponseEmailParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.VacationStatus;

public class VacationTimeResponseMailFormatter implements MailFormatter<VacationTimeResponseEmailParams> {
    private static final String APPROVED_TEXT = "Your vacation time request has been approved!";

    @Override
    public String getText(VacationTimeResponseEmailParams params) {
        String message = "";
        if (params.getStatus().equalsIgnoreCase(VacationStatus.APPROVED.label))
            message = APPROVED_TEXT;
        else
            message = getDeclinedText(params.getReason());
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

    private String getDeclinedText(String reason) {
        return "We regret to inform you that your vacation time request has been declined for following reason: <br>\n" +
                "\t\t" + reason;
    }

    @Override
    public String getSubject() {
        return "Vacation time";
    }
}
