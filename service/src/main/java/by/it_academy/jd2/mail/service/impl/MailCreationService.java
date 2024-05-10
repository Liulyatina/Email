package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import by.it_academy.jd2.mail.service.api.IMailCreationService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.converter.MailConverter;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class MailCreationService implements IMailCreationService {
    private final IMailDao mailDao;
    private final MailConverter mailConverter;
    private final MailSendService mailSendService;

    public MailCreationService(IMailDao mailDao, MailConverter mailConverter, MailSendService mailSendService) {
        this.mailDao = mailDao;
        this.mailConverter = mailConverter;
        this.mailSendService = mailSendService;
    }

    @Override
    @Transactional
    public void create(List<MailDTO> dtoList) {
        for (MailDTO dto : dtoList) {
            create(dto);
        }
    }

    @Override
    @Transactional
    public void create(MailDTO dto) {
        MailEntity mailEntity = convertDtoToEntity(dto);
        mailDao.save(mailEntity);
        mailSendService.send(dto);
    }

    private MailEntity convertDtoToEntity(MailDTO dto) {
        MailEntity mailEntity = mailConverter.toEntity(dto);
        mailEntity.setDtCreate(LocalDateTime.now());
        mailEntity.setStatus(MailStatus.LOADED);
        return mailEntity;
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
