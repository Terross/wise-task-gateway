package ru.leti.wise.task.gateway.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.leti.wise.task.gateway.security.service.AuthEntryPointJwt;
import ru.leti.wise.task.gateway.security.service.AuthTokenFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthTokenFilter authTokenFilter;
    private final AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request.requestMatchers("/graphql")
                        .permitAll().anyRequest().authenticated()
                )
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .cors().disable()
                .csrf().disable()
                .build();
    }
}
