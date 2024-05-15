package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IUserRepository;
import by.it_academy.jd2.mail.dao.entity.UserEntity;
import by.it_academy.jd2.mail.service.api.IUserRegistrationService;
import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.service.converter.UserConverter;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;
import jakarta.transaction.Transactional;


public class UserRegistrationService implements IUserRegistrationService {
    private final IUserRepository userRepository;
    private final UserConverter converter;
    private final WelcomeMailService welcomeMailService;


    public UserRegistrationService(IUserRepository userDao, UserConverter converter, WelcomeMailService welcomeMailService) {
        this.userRepository = userDao;
        this.converter = converter;
        this.welcomeMailService = welcomeMailService;
    }

    @Override
    @Transactional
    public void create(UserDto userDto){

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

        userRepository.save(userEntity);
    }
}