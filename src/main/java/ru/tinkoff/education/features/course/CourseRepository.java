package ru.tinkoff.education.features.course;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.education.features.course.entities.CourseEntity;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    List<CourseEntity> findAllByCategoryId(Integer catId);
}
