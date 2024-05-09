package by.it_academy.jd2.mail.service.impl;

import by.it_academy.jd2.mail.dao.api.IUserDao;
import by.it_academy.jd2.mail.dao.entity.UserEntity;
import by.it_academy.jd2.mail.service.api.IUserRegistrationService;
import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.service.converter.UserConverter;


public class UserRegistrationService implements IUserRegistrationService {
    private final IUserDao userDao;
    private final UserConverter converter;

    public UserRegistrationService(IUserDao userDao, UserConverter converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public void create(UserDto userDto) {

        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Логин должен быть обязательно указан");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Пароль должен быть обязательно указан");
        }
        if (userDto.getFullName() == null || userDto.getFullName().isBlank()) {
            throw new IllegalArgumentException("Имя должно быть обязательно указано");
        }
        if (userDao.getByLogin(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        UserEntity userEntity = converter.toEntity(userDto);

        userDao.create(userEntity);

    }
}