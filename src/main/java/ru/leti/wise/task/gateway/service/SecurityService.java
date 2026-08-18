package ru.leti.wise.task.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import ru.leti.wise.task.gateway.configuration.JwtProperties;
import ru.leti.wise.task.gateway.dto.UserCredentials;
import ru.leti.wise.task.gateway.dto.profile.ResetPasswordRequest;
import ru.leti.wise.task.gateway.dto.profile.SignInRequest;
import ru.leti.wise.task.gateway.dto.profile.SignUpRequest;
import ru.leti.wise.task.gateway.dto.profile.Token;
import ru.leti.wise.task.gateway.mapper.ProfileMapper;
import ru.leti.wise.task.gateway.service.grpc.profile.ProfileGrpcService;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final ProfileGrpcService profileGrpcService;
    private final ProfileMapper profileMapper;
    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtProperties;

    public Token signIn(SignInRequest request) {
        var profile = profileGrpcService.signIn(request.email(), request.password());
        var userDetails = new UserCredentials(profileMapper.toProfile(profile));
        return new Token(
                generateAccessToken(userDetails),
                generateRefreshToken(userDetails)
        );
    }

    public Token signUp(SignUpRequest request) {
        var profile = profileGrpcService.signUp(profileMapper.toProfile(request.profile()));
        UserCredentials userDetails = new UserCredentials(profileMapper.toProfile(profile));
        return new Token(
                generateAccessToken(userDetails),
                generateRefreshToken(userDetails)
        );
    }

    public Token resetPassword(ResetPasswordRequest request){
        var profile = profileGrpcService.resetPassword(request.recoveryToken(), request.newPassword());
        UserCredentials userDetails = new UserCredentials(profileMapper.toProfile(profile));
        return new Token(
                generateAccessToken(userDetails),
                generateRefreshToken(userDetails)
        );
    }

    private String generateAccessToken(UserCredentials user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("wise-task")
                .subject(user.getId())
                .issuedAt(now)
                .expiresAt(now.plus(jwtProperties.accessExpiresAt()))
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .claim("email", user.getEmail())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String generateRefreshToken(UserCredentials user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("wise-task")
                .subject(user.getId())
                .issuedAt(now)
                .expiresAt(now.plus(jwtProperties.refreshExpiresAt()))
                .claim("type", "refresh")
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}