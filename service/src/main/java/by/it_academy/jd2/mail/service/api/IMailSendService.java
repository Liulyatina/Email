package by.it_academy.jd2.mail.service.api;

import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;

public interface IMailSendService {

    void send(MailDTO dto) throws FailMailSendException;
}
