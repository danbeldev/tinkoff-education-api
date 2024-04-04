package ru.tinkoff.education.features.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.education.features.user.entitites.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);
}
