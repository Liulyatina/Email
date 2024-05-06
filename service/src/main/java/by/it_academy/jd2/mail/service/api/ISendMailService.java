package by.it_academy.jd2.mail.service.api;

public interface ISendMailService {
    boolean sendMail(String to, String subject, String body);
}
