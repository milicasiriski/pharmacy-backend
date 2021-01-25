package rs.ac.uns.ftn.isa.pharmacy.demo.mail;

public interface MailFormatter<T> {
    String getText(T params);
    String getSubject();
}
