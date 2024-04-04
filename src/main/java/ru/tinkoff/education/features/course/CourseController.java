package ru.tinkoff.education.features.course;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.tinkoff.education.features.course.dto.*;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearerAuth")
    private CourseDetailsDto add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "price") Float price,
            @RequestParam(name = "categoryId") Integer categoryId,
            @RequestParam(name = "back_image") MultipartFile backImage
    ) {
        CreateCourseParams params = new CreateCourseParams(title, description, price, categoryId);
        return courseDetailsMapper.toDto(courseService.add(params, backImage));
    }

    @PostMapping(value = "{id}/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearerAuth")
    private CourseVideoDto addVideo(
            @PathVariable Integer id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "video") MultipartFile video
    ) {
        return courseVideoMapper.toDto(courseService.addVideo(title, description, id, video));
    }
}
