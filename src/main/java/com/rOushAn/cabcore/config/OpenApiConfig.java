package com.rOushAn.cabcore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth"; // This name is used internally to reference your security scheme

        return new OpenAPI()
                .info(new Info()
                        .title("CabCore API") // Your application's title
                        .version("1.0") // API version
                        .description("API documentation for CabCore – a comprehensive ride-hailing backend system.") // Detailed description of your API
                        .termsOfService("https://github.com/1-RoushanKumar/Uber_Backend/blob/main/TERMS_OF_SERVICE.md") // **Suggest a real terms of service URL**
                        .contact(new Contact()
                                .name("Roushan Kumar")
                                .email("rk04393@gmail.com")
                                .url("https://github.com/1-RoushanKumar"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))) // **Corrected Apache 2.0 License URL**
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)) // Applies this security scheme globally to all endpoints
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, // The name must match the one used in addSecurityItem
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer") // Specifies the authentication scheme as 'Bearer'
                                        .bearerFormat("JWT") // Indicates that the bearer token is a JWT
                                        .description("Provide the JWT token obtained from the /auth/login endpoint. Example: `Bearer eyJhbGci...`"))); // **ADDED HELPFUL DESCRIPTION**
    }
}