package ru.leti.wise.task.gateway.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.gateway.security.configuration.JwtProperties;
import ru.leti.wise.task.gateway.security.user.UserCredentials;

import java.security.Key;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties jwtProperties;

    public String generateJwtToken(UserCredentials user) {
        return Jwts.builder()
                .setClaims(Map.of(
                        "id", user.getId(),
                        "role", user.getRole(),
                        "email", user.getEmail()
                ))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtProperties.expiration()))
                .signWith(SignatureAlgorithm.HS256, key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret()));
    }

    public String getClaimFromJwtToken(String token, String claim){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key()).build().parseClaimsJws(token).getBody();
        return claims.get(claim, String.class);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key()).parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }


}
