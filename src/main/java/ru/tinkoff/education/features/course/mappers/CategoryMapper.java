package ru.tinkoff.education.features.course.mappers;

import org.mapstruct.Mapper;
import ru.tinkoff.education.common.Mappable;
import ru.tinkoff.education.features.course.dto.CategoryDto;
import ru.tinkoff.education.features.course.entities.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends Mappable<CategoryEntity, CategoryDto> {}
