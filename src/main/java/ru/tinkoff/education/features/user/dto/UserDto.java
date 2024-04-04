package ru.tinkoff.education.features.user.dto;

import ru.tinkoff.education.features.user.entitites.UserEntity;

public record UserDto(
        Integer id,
        String username,
        String firstName,
        String lastName,
        String middleName,
        Float balance,
        UserEntity.Role role
) {}
