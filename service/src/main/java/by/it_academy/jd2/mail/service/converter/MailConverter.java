package by.it_academy.jd2.mail.service.converter;

import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.IConverter;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;

public class MailConverter implements IConverter<MailDTO, MailEntity> {
    @Override
    public MailEntity toEntity(MailDTO mailDTO) {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setRecipient(mailDTO.getRecipient());
        mailEntity.setSubject(mailDTO.getSubject());
        mailEntity.setText(mailDTO.getText());
        return mailEntity;
    }

    @Override
    public MailDTO toDTO(MailEntity mailEntity) {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setRecipient(mailEntity.getRecipient());
        mailDTO.setSubject(mailEntity.getSubject());
        mailDTO.setText(mailEntity.getText());
        return mailDTO;
    }
}
