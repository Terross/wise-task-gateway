package ru.leti.wise.task.gateway.service.grpc.profile;

import io.grpc.ClientInterceptor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.profile.ProfileServiceGrpc;

import static io.grpc.internal.ManagedChannelImplBuilder.forAddress;
import static ru.leti.wise.task.profile.ProfileServiceGrpc.newBlockingStub;

@Component
@RequiredArgsConstructor
public class ProfileStubHolder {

    private final ProfileGrpcProperties profileGrpcProperties;
    private final ClientInterceptor grpcTracingClientInterceptor;

    private ProfileServiceGrpc.ProfileServiceBlockingStub profileServiceStub;

    
    @PostConstruct
    void init() {
        profileServiceStub = newBlockingStub(forAddress(profileGrpcProperties.host(), profileGrpcProperties.port())
                .intercept(grpcTracingClientInterceptor)
                .usePlaintext().build());
    }

    ProfileServiceGrpc.ProfileServiceBlockingStub get() {
        return profileServiceStub;
    }
}
