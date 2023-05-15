package ru.leti.wise.task.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.ProfileMapper;
import ru.leti.wise.task.gateway.service.grpc.profile.ProfileGrpcService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileController implements GetAllProfilesQueryResolver, SignUpMutationResolver, SignInMutationResolver {

    private final ProfileGrpcService profileGrpcService;
    private final ProfileMapper profileMapper;

    @Override
    @QueryMapping
    public List<Profile> getAllProfiles() {
        return profileMapper.toProfiles(profileGrpcService.getAllProfiles());
    }

    @Override
    @MutationMapping
    public Token signIn(@Argument SignInRequest signInRequest) {
        var response = profileGrpcService.signIn(signInRequest.getEmail(), signInRequest.getPassword());
        return new Token(response);
    }

    @Override
    @MutationMapping
    public Token signUp(@Argument SignUpRequest signUpRequest) {
        var response = profileGrpcService.signUp(profileMapper.toProfile(signUpRequest.getProfile()));
        return new Token(response);
    }
}
