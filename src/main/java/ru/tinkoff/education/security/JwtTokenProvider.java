package ru.tinkoff.education.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.tinkoff.education.features.user.entitites.UserEntity;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private final SecretKey jwtAccessSecret;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtTokenProvider(
            @Value("${security.jwt.secret}") String jwtAccessSecret
    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
    }

    public String createAccessToken(@NonNull UserEntity user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusYears(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("role", user.getRole())
                .claim("id", user.getId())
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String getUsername(final String token) {
        return getAccessClaims(token).getSubject();
    }

    public Authentication getAuthorization(String token) {
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails,
                "",
                userDetails.getAuthorities());
    }
}
