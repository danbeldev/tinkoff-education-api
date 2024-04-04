package ru.tinkoff.education.features.course.mappers;

import org.mapstruct.Mapper;
import ru.tinkoff.education.common.Mappable;
import ru.tinkoff.education.features.course.dto.CourseDto;
import ru.tinkoff.education.features.course.entities.CourseEntity;

@Mapper(componentModel = "spring")
public interface CourseMapper extends Mappable<CourseEntity, CourseDto> { }
