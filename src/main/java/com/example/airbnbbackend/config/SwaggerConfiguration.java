package com.example.airbnbbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("에어비엔비 클론 API")
                        .description("에어비엔비 클론 서비스 API입니다.")
                        .version("0.0.0"));
    }
}
