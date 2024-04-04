package ru.tinkoff.education.features.course.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "students_courses_subscribers")
public class StudentCoursesSubscriberEntity implements Serializable {

    @EmbeddedId
    private StudentCoursesSubscriberIdEntity id;
}
