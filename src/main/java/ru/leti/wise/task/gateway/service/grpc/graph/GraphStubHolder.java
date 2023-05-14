package ru.leti.wise.task.gateway.service.grpc.graph;

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

    private GraphServiceGrpc.GraphServiceBlockingStub graphServiceStub;


    @PostConstruct
    void init() {
        graphServiceStub = newBlockingStub(forAddress(graphGrpcProperties.host(), graphGrpcProperties.port())
                .usePlaintext().build());
    }

    GraphServiceGrpc.GraphServiceBlockingStub get() {
        return graphServiceStub;
    }
}
