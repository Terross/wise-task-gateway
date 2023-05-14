package ru.leti.wise.task.gateway.service.grpc.graph;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc.service.graph")
public record GraphGrpcProperties(String host, int port) {
}
