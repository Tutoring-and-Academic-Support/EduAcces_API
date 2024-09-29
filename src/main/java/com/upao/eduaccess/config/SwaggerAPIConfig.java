package com.upao.eduaccess.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerAPIConfig {

    @Value("${eduaccess.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        // Servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        // Información de contacto
        Contact contact = new Contact();
        contact.setEmail("eduaccess-support@upao.edu");
        contact.setName("EduAccess Support");
        contact.setUrl("https://www.upao.edu");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        // Información general de la API
        Info info = new Info()
                .title("API EduAccess")
                .version("1.0")
                .contact(contact)
                .description("API Restful para la gestión de comentarios, materiales y pagos en el sistema EduAccess.")
                .termsOfService("https://www.upao.edu/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
