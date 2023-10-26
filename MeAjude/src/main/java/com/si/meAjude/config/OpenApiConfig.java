package com.si.meAjude.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI OpenApiConfig() {
        Server devServer = new Server().url("http://localhost:8081").description("Server URL in Development environment");
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        Info info = new Info().title("Me Ajude API")
                .description("API para o projeto Me Ajude.    " + "\n"
                        +  "     Disciplina DSC    " + "\n" + "Desnvolvido por:" + "\n" + "Pedro Vinicius , Anderson e Lucas")
                .version("v0.0.1")
                .contact(new Contact().name("Me Ajude"));



        return new OpenAPI().info(info).servers(List.of(devServer));
    }



}
