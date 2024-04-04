package ru.tinkoff.education.features.course;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.education.features.course.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {}
