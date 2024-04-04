package ru.tinkoff.education.features.course;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.education.features.course.entities.CourseVideoEntity;

import java.util.List;

public interface CourseVideoRepository extends JpaRepository<CourseVideoEntity, Integer> {

    List<CourseVideoEntity> findAllByCourseId(Integer id);
}
