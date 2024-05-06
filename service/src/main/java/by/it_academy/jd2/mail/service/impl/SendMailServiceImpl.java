package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.service.api.ISendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@Service
public class SendMailServiceImpl implements ISendMailService {
    private static final Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);

    private final String host;
    private final String port;
    private final String username;
    private final String password;

    public SendMailServiceImpl(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean sendMail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            logger.info("Email sent successfully to: {}", to);
            return true;

        } catch (AuthenticationFailedException e) {
            logger.error("Authentication failed. Username or password not accepted", e);
            return false;
        } catch (MessagingException e) {
            logger.error("Failed to send email", e);
            return false;
        }
    }
}