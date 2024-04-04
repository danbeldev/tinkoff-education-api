package ru.tinkoff.education.features.user.dto;

public record JwtRequestDto(
        String username,
        String password
) {}
