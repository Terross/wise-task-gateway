package ru.leti.wise.task.gateway.service.grpc.plugin;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc.service.plugin")
public record PluginGrpcProperties(String host, int port) {
}
