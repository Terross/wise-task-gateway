package ru.leti.wise.task.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.leti.wise.task.gateway.dto.profile.Profile;

import java.util.Collection;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class UserCredentials implements UserDetails {

    private String id;
    private String email;
    private String role;

    public UserCredentials(Profile profile) {
        this.id = profile.id();
        this.email = profile.email();
        this.role = profile.profileRole().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}