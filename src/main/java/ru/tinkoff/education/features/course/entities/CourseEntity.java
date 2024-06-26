package ru.tinkoff.education.features.course.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Float price;
    private String backImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Collection<CourseVideoEntity> videos;

    @OneToMany(mappedBy = "id.course", fetch = FetchType.LAZY)
    private Collection<StudentCoursesSubscriberEntity> subscriberCourses;
}
