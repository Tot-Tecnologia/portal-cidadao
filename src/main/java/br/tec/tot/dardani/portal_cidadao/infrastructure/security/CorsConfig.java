package br.tec.tot.dardani.portal_cidadao.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CorsConfig {

    @Value("${spring.cors.host}")
    private String cors;

    @Bean
    WebMvcConfigurer corsConfigurer() {
        log.info("SETUP CORS :: {}", cors);
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(cors)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Authorization", "Content-Type", "Access-Control-Max-Age", "3600",
                                "Access-Control-Allow-Credentials", "true")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
