package ru.tinkoff.education.features.course;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.education.features.course.entities.StudentCoursesSubscriberEntity;
import ru.tinkoff.education.features.course.entities.StudentCoursesSubscriberIdEntity;

public interface StudentCoursesSubscriberRepository extends JpaRepository<StudentCoursesSubscriberEntity, StudentCoursesSubscriberIdEntity> {}
