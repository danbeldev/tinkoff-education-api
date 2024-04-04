package ru.tinkoff.education.features.user.entitites;

import jakarta.persistence.*;
import lombok.Data;
import ru.tinkoff.education.features.course.entities.StudentCoursesSubscriberEntity;

import java.util.Collection;

@Data
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    private String password;
    private Float balance;
    private Role role;

    @OneToMany(mappedBy = "id.user", fetch = FetchType.LAZY)
    private Collection<StudentCoursesSubscriberEntity> subscriberCourses;

    public enum Role {
        STUDENT,
        TEACHER,
        ADMIN
    }
}
