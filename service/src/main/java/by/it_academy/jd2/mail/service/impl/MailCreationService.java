package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import by.it_academy.jd2.mail.service.api.IMailCreationService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.converter.MailConverter;

import java.util.List;

public class MailCreationService implements IMailCreationService {
    private final IMailDao mailDao;
    private final MailConverter mailConverter;

    public MailCreationService(IMailDao mailDao, MailConverter mailConverter) {
        this.mailDao = mailDao;
        this.mailConverter = mailConverter;
    }

    @Override
    public void create(List<MailDTO> dtoList) {
        for (MailDTO dto : dtoList) {
            MailEntity mailEntity = mailConverter.toEntity(dto);
            mailDao.save(mailEntity);
        }
    }

    @Override
    public void create(MailDTO dto) {
        MailEntity mailEntity = mailConverter.toEntity(dto);
        mailDao.save(mailEntity);
    }

    @Override

    public void update(MailEntity mail) {
        mailDao.update(mail);
    }

    @Override

    public List<MailEntity> getLoaded() {
        return mailDao.getByStatus(MailStatus.OK);
    }
}
