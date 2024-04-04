package ru.tinkoff.education.features.course;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.education.features.course.dto.CategoryDto;
import ru.tinkoff.education.features.course.dto.CourseDetailsDto;
import ru.tinkoff.education.features.course.dto.CourseDto;
import ru.tinkoff.education.features.course.dto.CourseVideoDto;
import ru.tinkoff.education.features.course.mappers.CategoryMapper;
import ru.tinkoff.education.features.course.mappers.CourseDetailsMapper;
import ru.tinkoff.education.features.course.mappers.CourseMapper;
import ru.tinkoff.education.features.course.mappers.CourseVideoMapper;
import ru.tinkoff.education.security.JwtEntity;

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

    @PostMapping("{id}/subscriber")
    @SecurityRequirement(name = "bearerAuth")
    private void subscriber(
            @PathVariable Integer id,
            @AuthenticationPrincipal JwtEntity jwt
    ) {
        courseService.subscriber(jwt.getId(), id);
    }

    @GetMapping("{id}/subscriber")
    @SecurityRequirement(name = "bearerAuth")
    private Boolean isSubscriber(
            @PathVariable Integer id,
            @AuthenticationPrincipal JwtEntity jwt
    ) {
        return courseService.isSubscriber(jwt.getId(), id);
    }
}
