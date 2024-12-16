package com.pawstime.pawstime.global.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "BASIC PAWSTIME API",
        version = "v1"
    ),
    security = @SecurityRequirement(name = "bearerAuth"),
    servers = {
        @io.swagger.v3.oas.annotations.servers.Server(
            url = "http://localhost:8080/",
            description = "local test"
        ),
        @io.swagger.v3.oas.annotations.servers.Server(
            url = "http://43.200.46.13:8080/",
            description = "dev test"
        )
    }
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {

}
