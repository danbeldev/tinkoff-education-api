package ru.tinkoff.education.features.user.mappers;

import org.mapstruct.Mapper;
import ru.tinkoff.education.common.Mappable;
import ru.tinkoff.education.features.user.dto.UserDto;
import ru.tinkoff.education.features.user.entitites.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<UserEntity, UserDto> {}
