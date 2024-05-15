package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IMailRepository;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import by.it_academy.jd2.mail.service.api.IMailCreationService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.converter.MailConverter;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MailCreationService implements IMailCreationService {
    private final IMailRepository mailRepository;
    private final MailConverter mailConverter;

    public MailCreationService(IMailRepository mailDao, MailConverter mailConverter, MailSenderService mailSendService) {
        this.mailRepository = mailDao;
        this.mailConverter = mailConverter;
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

        if (dto.getRecipient() == null) {
            throw new IllegalArgumentException("Ошибка при создании сообщения. Не указан отправитель");
        }

        if (dto.getSubject() == null) {
            throw new IllegalArgumentException("Ошибка при создании сообщения. Не указан получатель");
        }

        if (dto.getText() == null) {
            throw new IllegalArgumentException("Ошибка при создании сообщения. Отсутствует текст сообщения");
        }

        MailEntity mailEntity = convertDtoToEntity(dto);
        mailRepository.save(mailEntity);
    }

    public MailEntity convertDtoToEntity(MailDTO dto) {
        MailEntity mailEntity = mailConverter.toEntity(dto);
        mailEntity.setDtCreate(LocalDateTime.now());
        mailEntity.setStatus(MailStatus.LOADED);
        return mailEntity;
    }

    @Override
    public void update(MailEntity mail) {
        mailRepository.save(mail);
    }

    @Override
    public List<MailEntity> getLoaded() {
        return mailRepository.findByStatus(MailStatus.LOADED);
    }
}
