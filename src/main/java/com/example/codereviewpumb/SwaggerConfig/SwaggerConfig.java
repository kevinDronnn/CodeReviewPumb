package com.example.codereviewpumb.SwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI produceApi() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:8080")))
                .info(new Info()
                        .title("Code Review Pumb API")
                        .description("Це API для додавання тварин у сховище з CSV та XML файлів після валідації полів." +
                                "Також, є можливість отримувати тварин за критеріями та сортувати по будь якому стовпчику.")
                        .contact(new Contact().name("Артем Ворошилін")
                                .email("voroshilin777@icloud.com")));
    }


}