package ru.leti.wise.task.gateway.service.grpc.task;

import io.grpc.ClientInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskServiceGrpc;

import javax.annotation.PostConstruct;

import static io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.forAddress;
import static ru.leti.wise.task.task.TaskServiceGrpc.newBlockingStub;

@Component
@RequiredArgsConstructor
public class TaskStubHolder {

    private final TaskGrpcProperties taskGrpcProperties;
    private final ClientInterceptor grpcTracingClientInterceptor;

    private TaskServiceGrpc.TaskServiceBlockingStub taskServiceStub;


    @PostConstruct
    void init() {
        taskServiceStub = newBlockingStub(forAddress(taskGrpcProperties.host(), taskGrpcProperties.port())
                .intercept(grpcTracingClientInterceptor)
                .usePlaintext().build());
    }

    TaskServiceGrpc.TaskServiceBlockingStub get() {
        return taskServiceStub;
    }
}
