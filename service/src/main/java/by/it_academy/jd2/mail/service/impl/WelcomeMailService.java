package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailRepository;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import by.it_academy.jd2.mail.service.api.IConverter;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;

import java.time.LocalDateTime;

public class WelcomeMailService {

    private final IMailRepository mailRepository;

    private static final String WELCOME_SUBJECT = "Добро пожаловать в наше сообщество!";
    private static final String WELCOME_TEXT = "Дорогой пользователь,\n\nСпасибо за регистрацию в нашем приложении. Мы рады приветствовать вас!\n\nС уважением,\nКоманда приложения";


    public WelcomeMailService(IMailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public void saveWelcomeMail(String userEmail){
        MailEntity mailEntity = new MailEntity();
        mailEntity.setRecipient(userEmail);
        mailEntity.setSubject(WELCOME_SUBJECT);
        mailEntity.setText(WELCOME_TEXT);
        mailEntity.setDtCreate(LocalDateTime.now());
        mailEntity.setStatus(MailStatus.LOADED);

        mailRepository.save(mailEntity);
    }
}
