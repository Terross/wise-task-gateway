package ru.leti.wise.task.gateway.service.grpc.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.profile.ProfileServiceGrpc;

import javax.annotation.PostConstruct;

import static io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.forAddress;
import static ru.leti.wise.task.profile.ProfileServiceGrpc.newBlockingStub;

@Component
@RequiredArgsConstructor
public class ProfileStubHolder {

    private final ProfileGrpcProperties profileGrpcProperties;

    private ProfileServiceGrpc.ProfileServiceBlockingStub profileServiceStub;


    @PostConstruct
    void init() {
        profileServiceStub = newBlockingStub(forAddress(profileGrpcProperties.host(), profileGrpcProperties.port())
                .usePlaintext().build());
    }

    ProfileServiceGrpc.ProfileServiceBlockingStub get() {
        return profileServiceStub;
    }
}
