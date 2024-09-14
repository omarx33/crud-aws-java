package com.example.ejemplo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${ejemplo.openapi.prod-url}") //viene de properties
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        //Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development Environment");

        //Informacion de contacto
        Contact contact = new Contact();
        contact.setEmail("carlosmori@gmail.com");
        contact.setName("miempresa");
        contact.setUrl("https://www.youtube.com");

        //Informacion general de la API
        Info info = new Info()
                .title("Mi empresa Managment API")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints of Task mi empresa")
                .termsOfService("https://www.hampcode.com/terms");

        //Configuracion de seguridad con JWT
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("JWT Authentication");

        Components components = new Components()
                .addSecuritySchemes("bearerAuth", securityScheme);


        //Requerimiento de seguridad para utilizar operaciones
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .info(info)
                .components(components)
                .security(List.of(securityRequirement));

    }

}
