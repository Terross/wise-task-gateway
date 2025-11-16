package ru.leti.wise.task.gateway.security.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.leti.wise.task.gateway.security.service.AuthEntryPointJwt;
import ru.leti.wise.task.gateway.security.service.AuthTokenFilter;
import ru.leti.wise.task.gateway.security.user.UserCredentialsService;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthTokenFilter authTokenFilter;
    private final AuthEntryPointJwt authEntryPointJwt;
    private final UserCredentialsService userCredentialsService;
    private final CorsProperties corsProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request.requestMatchers("/graphql").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(withDefaults())
                .csrf().disable()
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }



    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(HttpSecurity security){
        AuthenticationManagerBuilder builder = security.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userCredentialsService);
        return builder.build();
    }

}
