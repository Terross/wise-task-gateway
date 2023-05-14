package ru.leti.wise.task.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.Profile;
import ru.leti.graphql.model.ProfilesQueryResolver;
import ru.leti.wise.task.gateway.mapper.ProfileMapper;
import ru.leti.wise.task.gateway.service.grpc.profile.ProfileGrpcService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileController implements ProfilesQueryResolver {

    private final ProfileGrpcService profileGrpcService;
    private final ProfileMapper profileMapper;

    @Override
    @QueryMapping
    public List<Profile> profiles() {
        return profileMapper.toProfiles(profileGrpcService.getAllProfiles());
    }
}
