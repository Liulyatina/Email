package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.IMailSenderService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MailSenderService implements IMailSenderService {
    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);
    private final String username;
    private final String password;
    private final Properties props;

    public MailSenderService(String host, String port, String username, String password) {
        this.username = username;
        this.password = password;

        this.props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }

    @Override
    public void send(MailEntity mail) {

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getRecipient()));
            message.setSubject(mail.getSubject());
            message.setText(mail.getText());

            Transport.send(message);

            logger.debug("Email sent successfully to: {}", mail.getRecipient());

        } catch (AuthenticationFailedException e) {
            logger.error("Authentication failed. Username or password not accepted", e);
        } catch (MessagingException e) {
            logger.error("Failed to send email", e);
        }
    }
}
