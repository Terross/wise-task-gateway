package ru.leti.wise.task.gateway.service.grpc.plugin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.plugin.PluginServiceGrpc;

import javax.annotation.PostConstruct;

import static io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.forAddress;
import static ru.leti.wise.task.plugin.PluginServiceGrpc.newBlockingStub;

@Component
@RequiredArgsConstructor
public class PluginStubHolder {

    private final PluginGrpcProperties pluginGrpcProperties;

    private PluginServiceGrpc.PluginServiceBlockingStub pluginServiceStub;


    @PostConstruct
    void init() {
        pluginServiceStub = newBlockingStub(forAddress(pluginGrpcProperties.host(), pluginGrpcProperties.port())
                .usePlaintext().build());
    }

    PluginServiceGrpc.PluginServiceBlockingStub get() {
        return pluginServiceStub;
    }
}
