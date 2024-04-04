package ru.tinkoff.education.features.course.mappers;

import org.mapstruct.Mapper;
import ru.tinkoff.education.common.Mappable;
import ru.tinkoff.education.features.course.dto.CourseVideoDto;
import ru.tinkoff.education.features.course.entities.CourseVideoEntity;

@Mapper(componentModel = "spring")
public interface CourseVideoMapper extends Mappable<CourseVideoEntity, CourseVideoDto> {}
