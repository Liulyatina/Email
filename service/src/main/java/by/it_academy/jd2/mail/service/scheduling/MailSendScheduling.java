package by.it_academy.jd2.mail.service.scheduling;

import by.it_academy.jd2.mail.service.job.MailSendJob;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MailSendScheduling {
    private final ScheduledExecutorService executorService;
    private final MailSendJob mailSendJob;

    public MailSendScheduling(int coreSize, long delay, TimeUnit unit, MailSendJob mailSendJob) {
        this.executorService = Executors.newScheduledThreadPool(coreSize);
        this.mailSendJob = mailSendJob;

        this.executorService.schedule(this.mailSendJob::start, delay, unit);
    }
}
