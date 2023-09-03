package ru.leti.wise.task.gateway.service.grpc.graph;

import io.grpc.ClientInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.graph.GraphServiceGrpc;

import javax.annotation.PostConstruct;

import static io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.forAddress;
import static ru.leti.wise.task.graph.GraphServiceGrpc.newBlockingStub;

@Component
@RequiredArgsConstructor
public class GraphStubHolder {

    private final GraphGrpcProperties graphGrpcProperties;
    private final ClientInterceptor grpcTracingClientInterceptor;

    private GraphServiceGrpc.GraphServiceBlockingStub graphServiceStub;


    @PostConstruct
    void init() {
        graphServiceStub = newBlockingStub(forAddress(graphGrpcProperties.host(), graphGrpcProperties.port())
                .intercept(grpcTracingClientInterceptor)
                .usePlaintext().build());
    }

    GraphServiceGrpc.GraphServiceBlockingStub get() {
        return graphServiceStub;
    }
}
