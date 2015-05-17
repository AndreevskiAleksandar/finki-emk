package mk.ukim.finki.wp.service.mail;

import mk.ukim.finki.wp.model.EmailMessage;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;

/**
 * @author Marija.Todorovska
 */
@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

  private final Logger log = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private VelocityEngine velocityEngine;

  @Override
  public void sendEmail(final EmailMessage email) {
    log.info("### SEND MAIL");
    try {
      prepareMailAndSend(email);
    } catch (Exception ex) {
      throw new RuntimeException("mail_sent_error", ex);
    }
  }

  @Override
  @Async
  public void sendEmailAsync(final EmailMessage email) {
    log.info("### SEND MAIL ASYNC");
    try {
      prepareMailAndSend(email);
    } catch (Exception ex) {
      log.error("Async send mail:", ex);
    }
  }

  private void prepareMailAndSend(final EmailMessage email) throws MailException {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      @Override
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setTo(email.getTo());
        message.setFrom(getFrom());
        if (email.getBcc() != null) {
          message.setBcc(email.getBcc());
        }
        message.setSubject(email.getSubject());
        String text = VelocityEngineUtils.mergeTemplateIntoString(
          velocityEngine,
          email.getTemplate(),
          "UTF8", email.getModel());
        message.setText(text, true);
      }
    };
    this.mailSender.send(preparator);
    System.out.println("### SEND MAIL - FINISHED");
  }

  private String getFrom() {
    return "admin@emk.finki.ukim.mk";
  }

}
