package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IUserDao;
import by.it_academy.jd2.mail.dao.entity.UserEntity;
import by.it_academy.jd2.mail.service.api.IMailSendService;
import by.it_academy.jd2.mail.service.api.IUserRegistrationService;
import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.service.converter.UserConverter;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;
import jakarta.transaction.Transactional;


public class UserRegistrationService implements IUserRegistrationService {
    private final IUserDao userDao;
    private final UserConverter converter;
    private final WelcomeMailService welcomeMailService;


    public UserRegistrationService(IUserDao userDao, UserConverter converter, WelcomeMailService welcomeMailService) {
        this.userDao = userDao;
        this.converter = converter;
        this.welcomeMailService = welcomeMailService;
    }

    @Override
    @Transactional
    public void create(UserDto userDto) throws FailMailSendException {

        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Логин должен быть обязательно указан");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Пароль должен быть обязательно указан");
        }
        if (userDto.getBirthday() == null) {
            throw new IllegalArgumentException("Дата рождения должна быть обязательно указана");
        }
        if (userDto.getFullName() == null || userDto.getFullName().isBlank()) {
            throw new IllegalArgumentException("Имя должно быть обязательно указано");
        }

        UserEntity userEntity = converter.toEntity(userDto);

        userDao.create(userEntity);

        welcomeMailService.sendWelcomeMail(userDto.getEmail());

    }
}