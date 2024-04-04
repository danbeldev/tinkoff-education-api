package ru.tinkoff.education.features.user.dto;

public record RegistrationRequestDto(
        String username,
        String firstName,
        String lastName,
        String middleName,
        String password
) {}
