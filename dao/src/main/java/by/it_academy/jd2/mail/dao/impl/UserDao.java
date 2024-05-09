package by.it_academy.jd2.mail.dao.impl;

import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.dao.api.IUserDao;
import by.it_academy.jd2.mail.dao.entity.UserEntity;

import java.util.Optional;

public class UserDao implements IUserDao {

    private static IUserDao userDao;
    @Override
    public void create(UserEntity userDto) {
        Optional<UserDto> byLogin = this.userDao.getByLogin(userDto.getEmail());

        if (byLogin.isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }
    }

    @Override
    public Optional<UserDto> getByLogin(String login) {
        return Optional.empty();
    }
}
