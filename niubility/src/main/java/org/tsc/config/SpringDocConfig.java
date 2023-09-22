package org.tsc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

// http://localhost:9090/swagger-ui/index.html#/
@Configuration
@OpenAPIDefinition(info = @Info(title = "Niubility Apis", description = "Niubility Backend Apis", version = "1.0.0"))
@SecurityScheme(name = "token", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SpringDocConfig {
}

