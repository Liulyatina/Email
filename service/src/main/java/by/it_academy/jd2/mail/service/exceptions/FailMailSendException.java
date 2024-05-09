package by.it_academy.jd2.mail.service.exceptions;

public class FailMailSendException extends Exception{
    public FailMailSendException() {
    }

    public FailMailSendException(String message) {
        super(message);
    }

    public FailMailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailMailSendException(Throwable cause) {
        super(cause);
    }
}
