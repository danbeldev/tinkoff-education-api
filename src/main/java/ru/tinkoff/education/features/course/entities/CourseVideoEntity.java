package ru.tinkoff.education.features.course.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "course_videos")
public class CourseVideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private UUID videoId;
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity course;
}
