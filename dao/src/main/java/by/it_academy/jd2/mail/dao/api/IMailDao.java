package by.it_academy.jd2.mail.dao.api;

import by.it_academy.jd2.mail.dao.entity.MailEntity;

import java.util.List;
import java.util.Optional;

public interface IMailDao {
    List<MailEntity> findAll(Integer page, Integer size);
    Optional<MailEntity> findById(Long id);
    void save(MailEntity email);
}
