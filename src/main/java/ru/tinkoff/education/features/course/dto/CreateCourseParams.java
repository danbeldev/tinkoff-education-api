package ru.tinkoff.education.features.course.dto;

public record CreateCourseParams(
        String title,
        String description,
        Float price,
        Integer categoryId
) {}
