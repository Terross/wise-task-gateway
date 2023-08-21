package ru.leti.wise.task.gateway.service.grpc.task;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc.service.task")
public record TaskGrpcProperties(String host, int port) {
}
