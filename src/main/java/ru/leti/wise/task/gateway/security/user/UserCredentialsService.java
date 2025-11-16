package ru.leti.wise.task.gateway.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.gateway.mapper.ProfileMapper;
import ru.leti.wise.task.gateway.service.grpc.profile.ProfileGrpcService;

@Component
@RequiredArgsConstructor
public class UserCredentialsService implements UserDetailsService {

    private final ProfileGrpcService profileGrpcService;
    private final ProfileMapper profileMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var profile = profileGrpcService.getProfileByEmail(login);
        return new UserCredentials(profileMapper.toProfile(profile));
    }
}
