package ru.leti.wise.task.gateway.service.grpc.plugin;

import io.grpc.ClientInterceptor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.plugin.PluginServiceGrpc;

import static io.grpc.internal.ManagedChannelImplBuilder.forAddress;
import static ru.leti.wise.task.plugin.PluginServiceGrpc.newBlockingStub;

@Component
@RequiredArgsConstructor
public class PluginStubHolder {

    private final PluginGrpcProperties pluginGrpcProperties;
    private final ClientInterceptor grpcTracingClientInterceptor;

    private PluginServiceGrpc.PluginServiceBlockingStub pluginServiceStub;


    @PostConstruct
    void init() {
        pluginServiceStub = newBlockingStub(forAddress(pluginGrpcProperties.host(), pluginGrpcProperties.port())
                .intercept(grpcTracingClientInterceptor)
                .usePlaintext().build());
    }

    PluginServiceGrpc.PluginServiceBlockingStub get() {
        return pluginServiceStub;
    }
}
