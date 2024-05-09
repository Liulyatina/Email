package by.it_academy.jd2.mail.service.converter;

import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.dao.entity.UserEntity;
import by.it_academy.jd2.mail.service.api.IConverter;

public class UserConverter implements IConverter<UserDto, UserEntity> {

    @Override
    public UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setBirthday(userDto.getBirthday());
        userEntity.setFullname(userDto.getFullName());
        return userEntity;
    }

    @Override
    public UserDto toDTO(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setBirthday(userEntity.getBirthday());
        userDto.setFullName(userEntity.getFullname());
        return userDto;
    }
}
