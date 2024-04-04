package ru.tinkoff.education.features.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.education.common.exceptions.BadRequestException;
import ru.tinkoff.education.common.exceptions.ResourceNotFoundException;
import ru.tinkoff.education.features.course.entities.*;
import ru.tinkoff.education.features.user.UserService;
import ru.tinkoff.education.features.user.entitites.UserEntity;

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
}
