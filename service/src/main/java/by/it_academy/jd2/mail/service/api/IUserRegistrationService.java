package by.it_academy.jd2.mail.service.api;

import by.it_academy.jd2.mail.core.dto.UserDto;

public interface IUserRegistrationService {
    void create(UserDto userDTO);
}
