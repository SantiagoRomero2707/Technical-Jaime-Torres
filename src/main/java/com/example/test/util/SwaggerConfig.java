package com.example.test.util;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(info =@Info(title = "Prueba Técnica ITS Solution", version = "1.0", description ="Prueba Tecnica que hace algo") )
public class SwaggerConfig {
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
