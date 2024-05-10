package by.it_academy.jd2.mail.dao.api;

import by.it_academy.jd2.mail.dao.entity.UserEntity;

import java.util.List;

public interface IUserDao {
    void create(UserEntity user);
    List<UserEntity> getByLogin(String login);
}