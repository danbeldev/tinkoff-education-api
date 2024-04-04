package ru.tinkoff.education.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Tinkoff education API",
                version = "0.0.1-SNAPSHOT"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "bearerAuth  (http, Bearer)",
        description = "JWT Authorization header using the Bearer scheme",
        scheme = "bearer"
)
public class OpenApiConfig {}
