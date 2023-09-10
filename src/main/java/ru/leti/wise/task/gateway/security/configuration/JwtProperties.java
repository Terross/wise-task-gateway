package ru.leti.wise.task.gateway.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(String secret, int expiration) {}
