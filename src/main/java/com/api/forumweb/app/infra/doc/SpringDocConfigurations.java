package com.api.forumweb.app.infra.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração para personalização da documentação do OpenAPI (Swagger).
 */
@Configuration
public class SpringDocConfigurations {

    /**
     * Define as configurações customizadas para a documentação OpenAPI.
     *
     * @return Um objeto OpenAPI configurado com esquema de segurança Bearer JWT.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
