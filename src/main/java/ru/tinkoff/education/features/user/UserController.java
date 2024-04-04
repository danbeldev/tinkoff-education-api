package ru.tinkoff.education.features.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.education.features.user.dto.JwtRequestDto;
import ru.tinkoff.education.features.user.dto.JwtResponseDto;
import ru.tinkoff.education.features.user.dto.RegistrationRequestDto;
import ru.tinkoff.education.features.user.dto.UserDto;
import ru.tinkoff.education.features.user.entitites.UserEntity;
import ru.tinkoff.education.features.user.mappers.UserMapper;
import ru.tinkoff.education.security.JwtEntity;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    private List<UserDto> getAll() {
        return userMapper.toDto(userService.getAll());
    }

    @PostMapping("login")
    private JwtResponseDto login(@RequestBody JwtRequestDto dto) {
        return userService.login(dto);
    }

    @PostMapping("student/reg")
    private JwtResponseDto reg(@RequestBody RegistrationRequestDto dto) {
        return userService.registration(dto, UserEntity.Role.STUDENT);
    }

    @GetMapping("balance")
    @SecurityRequirement(name = "bearerAuth")
    private Float getBalance(
            @AuthenticationPrincipal JwtEntity jwt
    ) {
        return userService.getBalance(jwt.getId());
    }

    @PostMapping("plus-balance")
    @SecurityRequirement(name = "bearerAuth")
    private Float plusBalance(
            @AuthenticationPrincipal JwtEntity jwt
    ){
        return userService.plusBalance(jwt.getId());
    }
}
