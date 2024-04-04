package ru.tinkoff.education.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tinkoff.education.features.user.entitites.UserEntity;

import java.util.Collection;

@Data
@AllArgsConstructor
public class JwtEntity implements UserDetails {

    private Integer id;
    private UserEntity.Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static JwtEntity create(final UserEntity user) {
        return new JwtEntity(
                user.getId(),
                user.getRole()
        );
    }
}
