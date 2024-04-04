package ru.tinkoff.education.features.course.dto;

public record CourseDto(
        Integer id,
        String title,
        String description,
        Float price
) {}
