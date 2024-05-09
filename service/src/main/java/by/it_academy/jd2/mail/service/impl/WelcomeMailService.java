package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.service.api.IMailSendService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;

public class WelcomeMailService {

    private final IMailSendService welcomeMessage;

    private static final String WELCOME_SUBJECT = "Добро пожаловать в наше сообщество!";
    private static final String WELCOME_TEXT = "Дорогой пользователь,\n\nСпасибо за регистрацию в нашем приложении. Мы рады приветствовать вас!\n\nС уважением,\nКоманда приложения";

    public WelcomeMailService(IMailSendService welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public void sendWelcomeMail(String userEmail) throws FailMailSendException {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setRecipient(userEmail);
        mailDTO.setSubject(WELCOME_SUBJECT);
        mailDTO.setText(WELCOME_TEXT);

        welcomeMessage.send(mailDTO);
    }
}
