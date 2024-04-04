package ru.tinkoff.education.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.education.common.exceptions.BadRequestException;
import ru.tinkoff.education.common.exceptions.ResourceNotFoundException;
import ru.tinkoff.education.features.user.dto.JwtRequestDto;
import ru.tinkoff.education.features.user.dto.JwtResponseDto;
import ru.tinkoff.education.features.user.dto.RegistrationRequestDto;
import ru.tinkoff.education.features.user.entitites.UserEntity;
import ru.tinkoff.education.security.JwtTokenProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Lazy
    private JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserEntity getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public JwtResponseDto login(JwtRequestDto dto) {
        JwtResponseDto jwtResponse = new JwtResponseDto();

        UserEntity user = getByUsername(dto.username());

        if(!passwordEncoder.matches(dto.password(), user.getPassword()))
            throw new BadRequestException("Invalid password");

        String accessToken = jwtTokenProvider.createAccessToken(user);
        jwtResponse.setId(user.getId());
        jwtResponse.setRole(user.getRole());
        jwtResponse.setAccessToken(accessToken);

        return jwtResponse;
    }

    @Transactional
    public JwtResponseDto registration(RegistrationRequestDto dto, UserEntity.Role role) {
        UserEntity user = new UserEntity();
        JwtResponseDto jwtResponse = new JwtResponseDto();

        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setMiddleName(dto.middleName());
        user.setBalance(0.0f);
        user.setRole(role);

        user = add(user);

        String accessToken = jwtTokenProvider.createAccessToken(user);
        jwtResponse.setId(user.getId());
        jwtResponse.setRole(user.getRole());
        jwtResponse.setAccessToken(accessToken);

        return jwtResponse;
    }

    @Transactional
    public UserEntity add(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new BadRequestException("Имя пользователя уже используеться");
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Float getBalance(Integer id) {
        UserEntity user = getById(id);
        return user.getBalance();
    }

    @Transactional
    public Float plusBalance(Integer id) {
        UserEntity user = getById(id);
        user.setBalance(user.getBalance() + 1000);
        return userRepository.save(user).getBalance();
    }
}
