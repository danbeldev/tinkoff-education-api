package ru.tinkoff.education.features.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.education.common.exceptions.ResourceNotFoundException;
import ru.tinkoff.education.features.course.entities.CategoryEntity;
import ru.tinkoff.education.features.course.entities.CourseEntity;
import ru.tinkoff.education.features.course.entities.CourseVideoEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final CourseVideoRepository courseVideoRepository;

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
}
