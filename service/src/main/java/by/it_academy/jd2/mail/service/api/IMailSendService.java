package by.it_academy.jd2.mail.service.api;

import by.it_academy.jd2.mail.service.api.dto.MailDTO;

public interface IMailSendService {

    void send(MailDTO mailDTO);
}
