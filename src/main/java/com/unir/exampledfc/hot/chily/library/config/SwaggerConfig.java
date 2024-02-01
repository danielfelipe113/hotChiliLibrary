package com.unir.exampledfc.hot.chily.library.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Search API")
                        .description("API to search Rent and User information in database")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Daniel, Bruna y Javier")
                                .email("swagger@unir.com")));

    }

}
