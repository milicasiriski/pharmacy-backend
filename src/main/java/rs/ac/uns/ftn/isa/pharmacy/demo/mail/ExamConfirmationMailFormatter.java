package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.TimeInterval;

import java.text.SimpleDateFormat;

public class ExamConfirmationMailFormatter implements MailFormatter<TimeInterval> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");

    @Override
    public String getText(TimeInterval params) {
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
                "\t\tCongratulations! Your exam appointment is confirmed! <br>\n" +
                "\t\tThe appointment starts at " + dateFormat.format(params.getStart().getTime()) +
                " and is scheduled to last until " + dateFormat.format(params.getEnd().getTime()) + "<br><br>\n" +
                "\t\tFor more information you can visit our website.\n" + "<br><br>\n" +
                "\t\tThank you for using our application!\n" +
                "\t</p>\n" +
                "\t\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    public String getSubject() {
        return "Dermatologist exam confirmed";
    }
}
