package by.it_academy.jd2.mail.service.api;

import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;

public interface IUserRegistrationService {
    void create(UserDto userDTO) throws FailMailSendException;
}
