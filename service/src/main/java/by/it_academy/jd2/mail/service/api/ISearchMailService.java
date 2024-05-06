package by.it_academy.jd2.mail.service.api;

import by.it_academy.jd2.mail.dao.entity.MailEntity;

import java.util.List;
import java.util.Optional;

public interface ISearchMailService {
    List<MailEntity> findAll(Integer page, Integer size);
    Optional<MailEntity> findById(Long id);
}
