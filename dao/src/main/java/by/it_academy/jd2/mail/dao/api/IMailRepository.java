package by.it_academy.jd2.mail.dao.api;

import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMailRepository extends JpaRepository<MailEntity, Long> {
    List<MailEntity> findByStatus(MailStatus status);
}
