package by.it_academy.jd2.mail.dao.api;

import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.dao.entity.UserEntity;

import java.util.Optional;

public interface IUserDao {
    void create(UserEntity user);

    Optional<UserDto> getByLogin(String login);
}