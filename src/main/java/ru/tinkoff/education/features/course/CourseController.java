package ru.tinkoff.education.features.course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.education.features.course.dto.CategoryDto;
import ru.tinkoff.education.features.course.dto.CourseDetailsDto;
import ru.tinkoff.education.features.course.dto.CourseDto;
import ru.tinkoff.education.features.course.dto.CourseVideoDto;
import ru.tinkoff.education.features.course.mappers.CategoryMapper;
import ru.tinkoff.education.features.course.mappers.CourseDetailsMapper;
import ru.tinkoff.education.features.course.mappers.CourseMapper;
import ru.tinkoff.education.features.course.mappers.CourseVideoMapper;

import java.util.List;

@RestController
@RequestMapping("course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final CourseMapper courseMapper;
    private final CategoryMapper categoryMapper;
    private final CourseVideoMapper courseVideoMapper;
    private final CourseDetailsMapper courseDetailsMapper;

    @GetMapping
    private List<CourseDto> getAll(
            @RequestParam Integer catId
    ) {
        return courseMapper.toDto(courseService.getAll(catId));
    }

    @GetMapping("{id}")
    private CourseDetailsDto getById(
            @PathVariable Integer id
    ) {
        return courseDetailsMapper.toDto(courseService.getById(id));
    }

    @GetMapping("/categories")
    private List<CategoryDto> getCategoryAll() {
        return categoryMapper.toDto(courseService.getCategoryAll());
    }

    @GetMapping("{id}/videos")
    private List<CourseVideoDto> getAllVideo(
            @PathVariable Integer id
    ) {
        return courseVideoMapper.toDto(courseService.getVideos(id));
    }
}
