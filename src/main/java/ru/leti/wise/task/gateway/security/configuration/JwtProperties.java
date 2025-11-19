package ru.leti.wise.task.gateway.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties("security.jwt")
public record JwtProperties(

        RSAPrivateKey privateKey,
        RSAPublicKey publicKey,
        int expiration

) {}
