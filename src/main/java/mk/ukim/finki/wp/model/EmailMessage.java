package mk.ukim.finki.wp.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marija.Todorovska
 */
public class EmailMessage {

    private String to;
    private Map<String, Object> model;
    private String template;
    private String subject;
    private String[] bcc;

    private EmailMessage() {
        model = new HashMap<String, Object>();
    }

    public static EmailMessage create() {
        return new EmailMessage();
    }

    public EmailMessage to(String to) {
        this.to = to;
        return this;
    }

    public EmailMessage subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailMessage template(String template) {
        this.template = template;
        return this;
    }

    public EmailMessage addToModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getTo() {
        return to;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public String getTemplate() {
        return template;
    }

    public String getSubject() {
        return subject;
    }

    public String[] getBcc() {
        return bcc;
    }

    public EmailMessage bcc(String[] bcc) {
        this.bcc = bcc;
        return this;
    }

}
