package by.it_academy.jd2.mail.service.job;

import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import by.it_academy.jd2.mail.service.api.IMailCreationService;
import by.it_academy.jd2.mail.service.api.IMailSendService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import by.it_academy.jd2.mail.service.converter.MailConverter;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;
import by.it_academy.jd2.mail.service.job.api.IJob;

import java.util.List;

public class MailSendJob implements IJob {

    private final IMailCreationService mailCreationService;
    private final IMailSendService mailSendService;
    private final MailConverter mailConverter;

    public MailSendJob(IMailCreationService mailCreationService, IMailSendService mailSendService, MailConverter mailConverter) {
        this.mailCreationService = mailCreationService;
        this.mailSendService = mailSendService;
        this.mailConverter = mailConverter;
    }

    @Override
    public void start() {
        List<MailEntity> loaded = mailCreationService.getLoaded();

        for (MailEntity mail : loaded) {
            try{
                MailDTO mailDTO = mailConverter.toDTO(mail);
                mailSendService.send(mailDTO);

                mail.setStatus(MailStatus.OK);
                mailCreationService.update(mail);
            } catch (FailMailSendException e) {
                mail.setStatus(MailStatus.ERROR);
                mailCreationService.update(mail);
            }
        }
    }
}
