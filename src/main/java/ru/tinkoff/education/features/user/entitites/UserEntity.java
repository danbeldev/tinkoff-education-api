package ru.tinkoff.education.features.user.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

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

    public enum Role {
        STUDENT,
        TEACHER,
        ADMIN
    }
}
