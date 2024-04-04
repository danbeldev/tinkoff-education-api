package ru.tinkoff.education.features.course;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.tinkoff.education.common.exceptions.BadRequestException;
import ru.tinkoff.education.common.exceptions.ResourceNotFoundException;
import ru.tinkoff.education.features.course.dto.CreateCourseParams;
import ru.tinkoff.education.features.course.entities.*;
import ru.tinkoff.education.features.user.UserService;
import ru.tinkoff.education.features.user.entitites.UserEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final CourseVideoRepository courseVideoRepository;
    private final StudentCoursesSubscriberRepository studentCoursesSubscriberRepository;

    private final UserService userService;

    @Transactional(readOnly = true)
    public List<CourseEntity> getAll(Integer catId) {
        return courseRepository.findAllByCategoryId(catId);
    }

    @Transactional(readOnly = true)
    public CourseEntity getById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Transactional(readOnly = true)
    public List<CategoryEntity> getCategoryAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryEntity getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Transactional(readOnly = true)
    public List<CourseVideoEntity> getVideos(Integer id) {
        return courseVideoRepository.findAllByCourseId(id);
    }

    @Transactional
    public void subscriber(Integer userId, Integer courseId) {
        StudentCoursesSubscriberIdEntity id = new StudentCoursesSubscriberIdEntity();
        UserEntity user = userService.getById(userId);
        CourseEntity course = getById(courseId);

        if(user.getBalance() < course.getPrice())
            throw new BadRequestException("Balance invalid");

        user.setBalance(user.getBalance()-course.getPrice());

        id.setCourse(course);
        id.setUser(user);
        StudentCoursesSubscriberEntity entity = new StudentCoursesSubscriberEntity();
        entity.setId(id);
        studentCoursesSubscriberRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Boolean isSubscriber(Integer userId, Integer courseId) {
        UserEntity user = userService.getById(userId);
        CourseEntity course = getById(courseId);
        StudentCoursesSubscriberIdEntity id = new StudentCoursesSubscriberIdEntity();
        id.setCourse(course);
        id.setUser(user);
        return studentCoursesSubscriberRepository.findById(id).isPresent();
    }

    @SneakyThrows
    @Transactional
    public CourseEntity add(CreateCourseParams params, MultipartFile file) {
        CourseEntity course = new CourseEntity();
        course.setPrice(params.price());
        CategoryEntity category = getCategoryById(params.categoryId());
        course.setCategory(category);
        course.setTitle(params.title());
        course.setDescription(params.description());
        String fileId = uploadFile(file);
        course.setBackImageId(fileId);
        return courseRepository.save(course);
    }

    @SneakyThrows
    @Transactional
    public CourseVideoEntity addVideo(
            String title,
            String description,
            Integer courseId,
            MultipartFile video
    ) {
        CourseEntity course = getById(courseId);
        CourseVideoEntity courseVideo = new CourseVideoEntity();
        courseVideo.setTitle(title);
        courseVideo.setDescription(description);
        String fileId = uploadFile(video);
        courseVideo.setVideoId(fileId);
        courseVideo.setDate(LocalDate.now());
        courseVideo.setCourse(course);
        return courseVideoRepository.save(courseVideo);
    }

    private String uploadFile(MultipartFile file) throws IOException {
        String folderPath = "classpath:/files/";
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(folderPath);
        File folder = resources[0].getFile();
        String fileName = file.getOriginalFilename();
        File newFile = new File(folder, fileName);
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
        return fileName;
    }
}
