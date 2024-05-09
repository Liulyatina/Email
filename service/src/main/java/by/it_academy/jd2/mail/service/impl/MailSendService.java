package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.service.api.IMailSendService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.converter.MailConverter;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MailSendService implements IMailSendService {
    private static final Logger logger = LoggerFactory.getLogger(MailSendService.class);

    private final IMailDao mailDao;
    private final MailConverter mailConverter;
    private final String username;
    private final String password;
    private final Properties props;

    public MailSendService(IMailDao mailDao, MailConverter mailConverter, String host, String port, String username, String password) {
        this.mailDao = mailDao;
        this.mailConverter = mailConverter;
        this.username = username;
        this.password = password;

        this.props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }

    @Override
    public void send(MailDTO mailDTO) {

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDTO.getRecipient()));
            message.setSubject(mailDTO.getSubject());
            message.setText(mailDTO.getText());

            Transport.send(message);

            logger.debug("Email sent successfully to: {}", mailDTO.getRecipient());

        } catch (AuthenticationFailedException e) {
            logger.error("Authentication failed. Username or password not accepted", e);
        } catch (MessagingException e) {
            logger.error("Failed to send email", e);
        }
    }
}
