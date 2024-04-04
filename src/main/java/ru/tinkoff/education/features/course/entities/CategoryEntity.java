package ru.tinkoff.education.features.course.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Collection<CourseEntity> courses;
}
