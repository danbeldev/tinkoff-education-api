package ru.tinkoff.education.features.user.dto;

import lombok.Data;
import ru.tinkoff.education.features.user.entitites.UserEntity;

@Data
public class JwtResponseDto {

    private Integer id;
    private UserEntity.Role role;
    private String accessToken;
}
