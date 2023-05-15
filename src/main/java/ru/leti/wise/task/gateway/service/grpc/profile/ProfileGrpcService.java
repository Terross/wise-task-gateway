package ru.leti.wise.task.gateway.service.grpc.profile;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.profile.ProfileGrpc;
import ru.leti.wise.task.profile.ProfileOuterClass.Profile;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ProfileGrpcService {

    private final ProfileStubHolder profileStubHolder;

    public List<Profile> getAllProfiles() {
        var request = Empty.newBuilder().build();

        return profileStubHolder.get().getAllProfiles(request).getProfileList();
    }

    public String signIn(String email, String password) {
        var request = ProfileGrpc.SignInRequest.newBuilder()
                .setEmail(email)
                .setPassword(password)
                .build();

        return profileStubHolder.get().signIn(request).getJWT();
    }

    public String signUp(Profile profile) {
        var request = ProfileGrpc.SignUpRequest.newBuilder()
                .setProfile(profile)
                .build();

        return profileStubHolder.get().signUp(request).getJWT();
    }
}
