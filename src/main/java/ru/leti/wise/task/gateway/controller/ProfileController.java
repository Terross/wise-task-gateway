package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.ProfileMapper;
import ru.leti.wise.task.gateway.security.service.SecurityService;
import ru.leti.wise.task.gateway.service.grpc.profile.ProfileGrpcService;

import java.util.List;

@Slf4j
@Observed
@Controller
@RequiredArgsConstructor
public class ProfileController implements GetAllProfilesQueryResolver, SignUpMutationResolver, SignInMutationResolver,
GetProfileQueryResolver, UpdateProfileMutationResolver, DeleteProfileMutationResolver, ChangePasswordMutationResolver, ResetPasswordMutationResolver, SendResetPasswordEmailMutationResolver {

    private final ProfileGrpcService profileGrpcService;
    private final ProfileMapper profileMapper;
    private final SecurityService securityService;

    @Override
    @QueryMapping
    @PreAuthorize("hasAnyRole(\"USER\",\"AUTHOR\",\"ADMIN\")")
    public List<Profile> getAllProfiles() {
        return profileMapper.toProfiles(profileGrpcService.getAllProfiles());
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public Token signIn(@Argument SignInRequest signInRequest) {
        return securityService.signIn(signInRequest);
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public Token signUp(@Argument SignUpRequest signUpRequest) {
        return securityService.signUp(signUpRequest);
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public String sendResetPasswordEmail(@Argument String email){
        profileGrpcService.sendResetPasswordEmail(email);
        return email;
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public Token resetPassword(@Argument ResetPasswordRequest resetPasswordRequest){
        return securityService.resetPassword(resetPasswordRequest);
    }

    @Override
    @MutationMapping
    @PreAuthorize("authentication.principal.profile.id.equals(#id) or hasRole(\"ADMIN\")")
    public String changePassword(@Argument String id, @Argument String oldPassword, @Argument String newPassword){
        profileGrpcService.changePassword(id,oldPassword,newPassword);
        return id;
    }

    @Override
    @QueryMapping
    @PreAuthorize("hasAnyRole(\"USER\",\"AUTHOR\",\"ADMIN\")")
    public Profile getProfile(@Argument String id) {
        return profileMapper.toProfile(profileGrpcService.getProfile(id));
    }

    @Override
    @MutationMapping
    @PreAuthorize("authentication.principal.profile.id.equals(#profile.id) or hasRole(\"ADMIN\")")
    public Profile updateProfile(@Argument ProfileInput profile) {
        return profileMapper.toProfile(profileGrpcService.updateProfile(profileMapper.toProfile(profile)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("authentication.principal.profile.id.equals(#id) or hasRole(\"ADMIN\")")
    public String deleteProfile(@Argument String id) {
        profileGrpcService.deleteProfile(id);
        return id;
    }
}
