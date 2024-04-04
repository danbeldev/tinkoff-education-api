package ru.tinkoff.education.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.education.features.user.dto.JwtRequestDto;
import ru.tinkoff.education.features.user.dto.JwtResponseDto;
import ru.tinkoff.education.features.user.dto.RegistrationRequestDto;
import ru.tinkoff.education.features.user.entitites.UserEntity;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    private JwtResponseDto login(@RequestBody JwtRequestDto dto) {
        return userService.login(dto);
    }

    @PostMapping("student/reg")
    private JwtResponseDto reg(@RequestBody RegistrationRequestDto dto) {
        return userService.registration(dto, UserEntity.Role.STUDENT);
    }
}
