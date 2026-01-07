package ru.leti.wise.task.gateway.service.grpc.statistic;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc.service.statistics")
public record StatisticsGrpcProperties(String host, int port) {
}
