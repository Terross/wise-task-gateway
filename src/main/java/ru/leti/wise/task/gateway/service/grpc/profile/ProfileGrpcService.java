package ru.leti.wise.task.gateway.service.grpc.profile;

import com.google.protobuf.Empty;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.profile.ProfileGrpc;
import ru.leti.wise.task.profile.ProfileOuterClass.Profile;

import java.util.List;


@Component
@Observed
@RequiredArgsConstructor
public class ProfileGrpcService {

    private final ProfileStubHolder profileStubHolder;

    public List<Profile> getAllProfiles() {
        var request = Empty.newBuilder().build();

        return profileStubHolder.get().getAllProfiles(request).getProfileList();
    }

    public Profile getProfile(String id) {
        var request = ProfileGrpc.GetProfileRequest.newBuilder()
                .setProfileId(id)
                .build();

        return profileStubHolder.get().getProfile(request).getProfile();
    }

    public Profile getProfileByEmail(String email) {
        var request = ProfileGrpc.GetProfileByEmailRequest.newBuilder()
                .setEmail(email)
                .build();

        return profileStubHolder.get().getProfileByEmail(request).getProfile();
    }

    public void deleteProfile(String id) {
        var request = ProfileGrpc.DeleteProfileRequest.newBuilder()
                .setProfileId(id)
                .build();

        profileStubHolder.get().deleteProfile(request);
    }

    public Profile updateProfile(Profile profile) {
        var request = ProfileGrpc.UpdateProfileRequest.newBuilder()
                .setProfile(profile)
                .build();

        return profileStubHolder.get().updateProfile(request).getProfile();
    }

    public Profile signIn(String email, String password) {
        var request = ProfileGrpc.SignInRequest.newBuilder()
                .setEmail(email)
                .setPassword(password)
                .build();

        return profileStubHolder.get().signIn(request).getProfile();
    }

    public Profile signUp(Profile profile) {
        var request = ProfileGrpc.SignUpRequest.newBuilder()
                .setProfile(profile)
                .build();

        return profileStubHolder.get().signUp(request).getProfile();
    }

    public Profile resetPassword(String recoveryToken, String newPassword) {
        var request = ProfileGrpc.ResetPasswordRequest.newBuilder()
                .setRecoveryToken(recoveryToken)
                .setNewPassword(newPassword)
                .build();

        return profileStubHolder.get().resetPassword(request).getProfile();
    }

    public void sendResetPasswordEmail(String email) {
        var request = ProfileGrpc.SendResetPasswordEmailRequest.newBuilder()
                .setEmail(email)
                .build();

        profileStubHolder.get().sendResetPasswordEmail(request);
    }

    public void changePassword(String id, String oldPassword, String newPassword) {
        var request = ProfileGrpc.ChangePasswordRequest.newBuilder().setProfileId(id)
                .setOldPassword(oldPassword)
                .setNewPassword(newPassword)
                .build();

        profileStubHolder.get().changePassword(request);
    }


}
