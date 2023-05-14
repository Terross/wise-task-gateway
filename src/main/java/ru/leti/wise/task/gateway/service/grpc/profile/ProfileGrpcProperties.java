package ru.leti.wise.task.gateway.service.grpc.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc.service.profile")
public record ProfileGrpcProperties(String host, int port) {
}
