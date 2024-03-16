package com.nomad.main.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nomad Api Doc")
                        .description("API documentation based on SpringDoc.")
                        .version("v1.1.0")
//                        .license(new License()
//                                .name("许可协议")
//                                .url("url"))
//                        .contact(new Contact()
//                                .name("JiaPeng")
//                                .email("email"))
                ).externalDocs(new ExternalDocumentation()
                        .description("Document link")
                        .url("http://localhost:8080/doc/swagger-ui/index.html"));
    }
}
