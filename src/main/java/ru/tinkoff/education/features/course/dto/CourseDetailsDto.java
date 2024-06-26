package ru.tinkoff.education.features.course.dto;

import java.util.Collection;

public record CourseDetailsDto(
        Integer id,
        String title,
        String description,
        Float price,
        String backImageId,
        CategoryDto category,
        Collection<CourseVideoDto> videos
) {}
