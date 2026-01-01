package ru.leti.wise.task.gateway.service.grpc.event;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc.service.event")
public record EventGrpcProperties(String host, int port) {
}
