package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.ISaveMailService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.converter.MailConverter;
import jakarta.transaction.Transactional;

public class SaveMailServiceImpl implements ISaveMailService {
    private final IMailDao mailDao;
    private final MailConverter mailConverter;

    public SaveMailServiceImpl(IMailDao mailDao, MailConverter mailConverter) {
        this.mailDao = mailDao;
        this.mailConverter = mailConverter;
    }

    @Override
    public void saveMail(MailDTO mailDTO) {
        MailEntity mailEntity = mailConverter.toEntity(mailDTO);
        mailDao.save(mailEntity);
    }
}