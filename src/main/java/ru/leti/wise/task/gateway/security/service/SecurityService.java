package ru.leti.wise.task.gateway.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leti.graphql.model.SignInRequest;
import ru.leti.graphql.model.SignUpRequest;
import ru.leti.graphql.model.Token;
import ru.leti.wise.task.gateway.mapper.ProfileMapper;
import ru.leti.wise.task.gateway.security.user.UserDetailsImpl;
import ru.leti.wise.task.gateway.service.grpc.profile.ProfileGrpcService;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final ProfileGrpcService profileGrpcService;
    private final ProfileMapper profileMapper;
    private final JwtUtils jwtUtils;

    public Token signIn(SignInRequest request) {
        var profile = profileGrpcService.signIn(request.getEmail(), request.getPassword());
        UserDetailsImpl userDetails = new UserDetailsImpl(profileMapper.toProfile(profile));
        return Token.builder()
                .setToken(jwtUtils.generateJwtToken(userDetails))
                .build();
    }

    public Token signUp(SignUpRequest request) {
        var profile = profileGrpcService.signUp(profileMapper.toProfile(request.getProfile()));
        UserDetailsImpl userDetails = new UserDetailsImpl(profileMapper.toProfile(profile));
        return Token.builder()
                .setToken(jwtUtils.generateJwtToken(userDetails))
                .build();
    }
}
