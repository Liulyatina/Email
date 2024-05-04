package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.ISearchMailService;

import java.util.List;
import java.util.Optional;

public class SearchMailService implements ISearchMailService {

    public SearchMailService(IMailDao mailDao) {
        this.mailDao = mailDao;
    }

    private final IMailDao mailDao;

    @Override
    public List<MailEntity> findAll(Integer page, Integer size) {
        return mailDao.findAll(page, size);
    }

    @Override
    public Optional<MailEntity> findById(Long id) {
        return mailDao.findById(id);
    }
}
