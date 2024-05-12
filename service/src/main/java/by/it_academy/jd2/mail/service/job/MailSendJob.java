package by.it_academy.jd2.mail.service.job;

import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import by.it_academy.jd2.mail.service.api.IMailCreationService;
import by.it_academy.jd2.mail.service.api.IMailSenderService;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;
import by.it_academy.jd2.mail.service.job.api.IJob;

import java.util.List;

public class MailSendJob implements IJob {

    private final IMailCreationService mailCreationService;
    private final IMailSenderService mailSender;

    public MailSendJob(IMailCreationService mailCreationService, IMailSenderService mailSender) {
        this.mailCreationService = mailCreationService;
        this.mailSender = mailSender;
    }

    @Override
    public void start() {
        List<MailEntity> loaded = mailCreationService.getLoaded();

        for (MailEntity mail : loaded) {

            try{
                this.mailSender.send(mail);
                mail.setStatus(MailStatus.OK);
            } catch (FailMailSendException e) {
                mail.setStatus(MailStatus.ERROR);
            } finally {
                this.mailCreationService.update(mail);
            }
        }
    }
}
