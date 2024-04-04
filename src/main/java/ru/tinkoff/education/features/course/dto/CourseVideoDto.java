package ru.tinkoff.education.features.course.dto;

import java.time.LocalDate;

public record CourseVideoDto(
        Integer id,
        String title,
        String description,
        String videoId,
        LocalDate date
) {}
