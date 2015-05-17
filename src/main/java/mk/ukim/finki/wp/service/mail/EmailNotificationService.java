package mk.ukim.finki.wp.service.mail;

import mk.ukim.finki.wp.model.EmailMessage;

/**
 *
 * @author Marija.Todorovska
 */
public interface EmailNotificationService {

    void sendEmail(final EmailMessage email);
    
    void sendEmailAsync(final EmailMessage email);

}
