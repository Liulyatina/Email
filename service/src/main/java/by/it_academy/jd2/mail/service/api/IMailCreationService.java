package by.it_academy.jd2.mail.service.api;

import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;

import java.util.List;
import java.util.Optional;

public interface IMailCreationService {
    List<MailEntity> findAll(Integer page, Integer size);
    Optional<MailEntity> findById(Long id);
    void create(List<MailDTO> mailDTO);
    void create(MailDTO mailDTO);
    void update(MailEntity mailEntity);
    List<MailEntity> getLoaded();
}
