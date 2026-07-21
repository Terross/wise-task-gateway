package ru.leti.wise.task.gateway.service.grpc.graph;

import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.graph.ReactorGraphServiceGrpc;

@Slf4j
@Component
@RequiredArgsConstructor
public class GraphStubHolder {

    private final GraphGrpcProperties graphGrpcProperties;
    private final ClientInterceptor grpcTracingClientInterceptor;

    private ReactorGraphServiceGrpc.ReactorGraphServiceStub graphServiceStub;

    @PostConstruct
    void init() {
        String host = graphGrpcProperties.host();
        int port = graphGrpcProperties.port();

        log.info("Initializing REACTIVE Graph gRPC stub with host: {}, port: {}", host, port);

        if (host == null || host.isBlank()) {
            throw new IllegalStateException("Graph gRPC host is not configured");
        }
        if (port <= 0) {
            throw new IllegalStateException("Graph gRPC port is not configured: " + port);
        }

        graphServiceStub = ReactorGraphServiceGrpc.newReactorStub(
                ManagedChannelBuilder.forAddress(host, port)
                        .intercept(grpcTracingClientInterceptor)
                        .usePlaintext()
                        .build()
        );

        log.info("Graph gRPC reactive stub initialized successfully");
    }

    ReactorGraphServiceGrpc.ReactorGraphServiceStub get() {
        if (graphServiceStub == null) {
            throw new IllegalStateException("Graph gRPC stub not initialized");
        }
        return graphServiceStub;
    }
}