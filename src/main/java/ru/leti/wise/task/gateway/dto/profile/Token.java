package ru.leti.wise.task.gateway.dto.profile;

public record Token(
        String accessToken,
        String refreshToken
) {}