package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailRepository;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.ISearchMailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class SearchMailService implements ISearchMailService {
    private final IMailRepository mailRepository;

    public SearchMailService(IMailRepository mailDao) {
        this.mailRepository = mailDao;
    }
    @Override
    public List<MailEntity> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return mailRepository.findAll(pageable).getContent();
    }
    @Override
    public Optional<MailEntity> findById(Long id) {
        return mailRepository.findById(id);
    }
}
