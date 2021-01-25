package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

public class MailService<T> {
    private Environment env;
    private JavaMailSender javaMailSender;
    private MailFormatter<T> mailFormatter;

    public MailService(Environment env, JavaMailSender javaMailSender, MailFormatter<T> mailFormatter) {
        this.env = env;
        this.javaMailSender = javaMailSender;
        this.mailFormatter = mailFormatter;
    }

    @Async
    public void sendMail(String recipient, T params) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message);
        mail.setTo(recipient);
        mail.setFrom(Objects.requireNonNull(this.env.getProperty("spring.mail.username")));

        mail.setSubject(mailFormatter.getSubject());
        mail.setText(mailFormatter.getText(params), true);

        javaMailSender.send(message);
    }
}
