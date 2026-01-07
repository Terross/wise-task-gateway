package ru.leti.wise.task.gateway.configurtaion;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.leti.wise.task.gateway.security.configuration.CorsProperties;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {
    private final CorsProperties corsProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration registration = registry.addMapping("/**");

        if (corsProperties.allowedOrigins() != null) {
            registration.allowedOrigins(corsProperties.allowedOrigins().toArray(new String[0]));
        }
        if (corsProperties.allowCredentials() != null) {
            registration.allowCredentials(corsProperties.allowCredentials());
        }
        if (corsProperties.allowedMethods() != null) {
            registration.allowedMethods(corsProperties.allowedMethods().toArray(new String[0]));
        }
        if (corsProperties.allowedHeaders() != null) {
            registration.allowedHeaders(corsProperties.allowedHeaders().toArray(new String[0]));
        }
        if (corsProperties.maxAge() != null) {
            registration.maxAge(corsProperties.maxAge());
        }
    }
}
