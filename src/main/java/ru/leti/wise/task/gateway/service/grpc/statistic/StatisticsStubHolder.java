package ru.leti.wise.task.gateway.service.grpc.statistic;

import io.grpc.ClientInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.event.StatisticsServiceGrpc;

import javax.annotation.PostConstruct;

import static io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.forAddress;
import static ru.leti.wise.task.event.StatisticsServiceGrpc.newBlockingStub;


@Component
@RequiredArgsConstructor
public class StatisticsStubHolder {
    private final StatisticsGrpcProperties statisticsGrpcProperties;
    private final ClientInterceptor grpcTracingClientInterceptor;

    private StatisticsServiceGrpc.StatisticsServiceBlockingStub statisticsServiceStub;


    @PostConstruct
    void init() {
        statisticsServiceStub = newBlockingStub(forAddress(statisticsGrpcProperties.host(), statisticsGrpcProperties.port())
                .intercept(grpcTracingClientInterceptor)
                .usePlaintext().build());
    }

    StatisticsServiceGrpc.StatisticsServiceBlockingStub get() {
        return statisticsServiceStub;
    }
}
