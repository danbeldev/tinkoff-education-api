package ru.tinkoff.education.features.course.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "course_videos")
public class CourseVideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String videoId;
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity course;
}
